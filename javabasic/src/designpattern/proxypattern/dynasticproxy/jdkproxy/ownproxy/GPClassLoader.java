package designpattern.proxypattern.dynasticproxy.jdkproxy.ownproxy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.proxypattern.dynasticproxy.jdkproxy.ownproxy
 * @Description:
 * @Date: 2021/7/30 16:20
 */
public class GPClassLoader extends ClassLoader {

    private File classpathFile;

    public GPClassLoader() {
        String classpath = GPClassLoader.class.getResource("").getPath();
        this.classpathFile = new File(classpath);
    }

    protected Class<?> findClass(String name) {
        String className = GPClassLoader.class.getPackage().getName() + "." + name;
        if (classpathFile != null) {
            File classFile = new File(classpathFile, name.replaceAll("\\.", "/") + ".class");
            if (classFile.exists()) {
                FileInputStream in = null;
                ByteArrayOutputStream bos = null;
                try {
                    in = new FileInputStream(classFile);
                    bos = new ByteArrayOutputStream();
                    byte[] buff = new byte[1024];
                    int len;
                    while ((len = in.read(buff)) != -1) {
                        bos.write(buff, 0, len);
                    }
                    return defineClass(className, bos.toByteArray(), 0, bos.size());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if (bos != null) {
                        try {
                            bos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }
        return null;
    }
}
