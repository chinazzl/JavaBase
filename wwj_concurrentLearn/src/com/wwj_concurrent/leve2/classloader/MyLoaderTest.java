package com.wwj_concurrent.leve2.classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**********************************
 * @author zhang zhao lin
 * @date 2021年08月30日 23:10
 * @Description 测试 自定义加载器
 **********************************/
public class MyLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        MyClassLoader myClassLoader1 = new MyClassLoader("myClassLoader-1");
        Class<?> loadClass = myClassLoader1.loadClass("com.wwj_concurrent.leve2.classloader.MyObject");

        System.out.println(loadClass);
        System.out.println(loadClass.getClassLoader());

        Object instance = loadClass.newInstance();
        Method method = loadClass.getMethod("hello",new Class[]{});
        Object result = method.invoke(instance,new Object[]{});
        System.out.println(result);
    }
}
