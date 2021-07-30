package com.wwj_concurrent.leve2.classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.leve2.classloader
 * @Description:
 * @Date: 2021/6/16 16:11
 */
public class MyClassLoaderTest {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException,
            ClassNotFoundException, NoSuchMethodException, InvocationTargetException {

        /*
            调用loadClass并不会导致类的初始化，只是执行了加载过程中的加载阶段。
            不会打印HelloWorld 的静态代码块中的代码。
         */
        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> helloWorldClass = myClassLoader.loadClass("com.wwj_concurrent.leve2.classloader.HelloWorld");
        System.out.println(helloWorldClass.getClassLoader());

        // 1.
      /*  Object instance = helloWorldClass.newInstance();
        System.out.println(instance);

        Method method = helloWorldClass.getMethod("welcome");
        String invoke = (String) method.invoke(instance);
        System.out.println(invoke);*/


    }
}
