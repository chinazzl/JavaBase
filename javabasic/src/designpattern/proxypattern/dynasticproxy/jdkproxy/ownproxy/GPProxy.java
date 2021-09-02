package designpattern.proxypattern.dynasticproxy.jdkproxy.ownproxy;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.proxypattern.dynasticproxy.jdkproxy.ownproxy
 * @Description:
 * @Date: 2021/7/30 14:10
 */
public class GPProxy {
    public static final String ln = "\r\n";
    public static Object newProxyInstance(GPClassLoader classLoader, Class<?>[] interfaces, GPInvocationHandler h) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //动态生成.java代码
        String src = generateSrc(interfaces);
        //Java文件输出磁盘
        String filePath = GPProxy.class.getResource("").getPath();
        File file = new File(filePath + "$Proxy0.java");
        FileWriter fw = new FileWriter(file);
        fw.write(src);
        fw.flush();
        fw.close();
        //把生成.java文件编译成.class文件
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager manager = compiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> iterable = manager.getJavaFileObjects(file);
        JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, null, null, iterable);
        task.call();
        manager.close();

        //把编译生成的class文件加载到JVM中
        Class<?> proxyClass = classLoader.findClass("$Proxy0");
        Constructor<?> c = proxyClass.getConstructor(GPInvocationHandler.class);
        file.delete();
        //返回字节码重组以后的新代理对象
        return c.newInstance(h);

    }

    private static String generateSrc(Class<?>[] interfaces) {
        StringBuilder sb = new StringBuilder();
        sb.append("package designpattern.proxypattern.dynasticproxy.jdkproxy.ownproxy;" + ln);
        sb.append("import designpattern.proxypattern.dynasticproxy.jdkproxy.Person;" + ln);
        sb.append("import java.lang.reflect.*;" + ln);
        sb.append("public class $Proxy0 implements " + interfaces[0].getName() + "{" + ln);
        //创建构造
        sb.append("GPInvocationHandler h;" + ln);
        sb.append("public $Proxy0(GPInvocationHandler h) { " + ln);
        sb.append("this.h = h;" + ln);
        sb.append("}" + ln);

        for (Method method : interfaces[0].getMethods()) {
            Class<?>[] params = method.getParameterTypes();
            StringBuilder paramNames = new StringBuilder();
            StringBuilder paramValues = new StringBuilder();
            StringBuilder paramClasses = new StringBuilder();
            for (int i = 0; i < params.length; i++) {
                Class clazz = params[i];
                String type = clazz.getName();
                String paramName = toLowerFirstCase(clazz.getSimpleName());
                paramNames.append(type).append(" ").append(paramName);
                paramValues.append(paramName);
                paramClasses.append(clazz.getName()).append(".class");
                if (i > 1 && i < params.length - 1) {
                    paramNames.append(",");
                    paramValues.append(",");
                    paramClasses.append(",");
                }
            }
            sb.append("public " + method.getReturnType().getName() + " " + method.getName()
                    + "(" + paramNames.toString() + ") { " + ln);
            sb.append("try { " + ln);
            sb.append("Method m = " + interfaces[0].getName() + ".class.getMethod(\"" + method.getName() + "\",new Class[] {" + paramClasses.toString() + "});" + ln);
            sb.append((hasReturnValue(method.getReturnType()) ? "return " : "") + getCaseCode("this.h.invoke(this,m,new Object[]{" + paramValues + "})"
                    , method.getReturnType()) + ";" + ln);
            sb.append("}catch(Error _ex) { }");
            sb.append("catch(Throwable e){" + ln);
            sb.append("throw new UndeclaredThrowableException(e);" + ln);
            sb.append("}");
            sb.append(getReturnEmptyCode(method.getReturnType()));
          sb.append("}");
        }
        sb.append("}" + ln);
        return sb.toString();
    }

    private static String getReturnEmptyCode(Class<?> returnClass) {
        if (mappings.containsKey(returnClass)) {
            return "return 0;";
        } else if (returnClass == void.class) {
            return "";
        } else {
            return "return null;";
        }
    }

    private static Map<Class, Class> mappings = new HashMap<>();

    static {
        mappings.put(int.class, Integer.class);
    }

    private static String getCaseCode(String code, Class<?> returnClass) {
        if (mappings.containsKey(returnClass)) {
            return "((" + mappings.get(returnClass).getName() + ")" + code + ")." + returnClass.getSimpleName() + "Value()";
        }
        return code;
    }

    private static boolean hasReturnValue(Class<?> returnType) {
        return returnType != void.class;
    }

    private static String toLowerFirstCase(String name) {
        char[] chars = name.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

}
