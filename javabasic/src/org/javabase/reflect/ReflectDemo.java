package org.javabase.reflect;


import langer.Collector;
import langer.StrigTrend;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Created by Felix on 2017/8/4.
 * 调用`Class`对象的`getConstructor(Class... parameterTypes)`获取构造方法对象
 - 调用是构造方法类`Constructor`的`newInstance(Object... initargs)`方法新建对象
 - 调用`Class`对象的`getMethod(String name, Class... parameterTypes)`获取方法对象
 - 调用方法对象类`Method`的`invoke(Object obj, Object... args)`方法，调用对象上相应方法
 *
 */
public class ReflectDemo {
    public static void main(String[] args) throws NoSuchMethodException, ClassNotFoundException {
        //类名.class
//        Class<Collector> collectorClass = Collector.class;
       /*
        对象名.getClass（）
        */
//        StrigTrend strigTrend = new StrigTrend();
//        Class<? extends StrigTrend> aClass = strigTrend.getClass();
//        Method main = aClass.getMethod("change");
//        String name = main.getName();
//        System.out.println(name);
    /*
        Class.forName();
     */
        Class<?> strigTrend = Class.forName("langer.StrigTrend");
        Constructor<?>[] constructors = strigTrend.getConstructors();
        //只有无参构造和有参构造都写的情况下才能正常运行，否则会暴索引越界异常
        String name = constructors[1].getName();
        System.out.println(name);
    }
}
