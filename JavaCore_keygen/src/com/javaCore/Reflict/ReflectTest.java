package com.javaCore.Reflict;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectTest {

    public static void main(String[] args) throws ClassNotFoundException,
            IllegalAccessException,
            InstantiationException,
            NoSuchMethodException,
            InvocationTargetException {
        //获取Class的三种方式
        Person p = new Person("a","b");
//        Class<? extends Person> aClass = p.getClass();
//        Class<Person> aClass = Person.class;

        Class<?> aClass = Class.forName("com.javaCore.Reflict.Person");
        //获取声明的方法
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod.getName());
        }

        //获取该Class中的所有成员变量
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField.getName());
        }

        // 通过Class创建对象,前提要有空的构造函数
        Person person = (Person) aClass.newInstance();
        // 获取构造方法并创建对象
        Constructor<?> p_construct = aClass.getDeclaredConstructor(String.class, String.class);
        Person p2 = (Person) p_construct.newInstance("hehe", "aaa");
        System.out.println(p2.getName());
    }
}
