package org.javabase.classLoaderDemo;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Felix on 2017/8/7.
 * 首先要明确的是java的类加载器是按照需求来加载类的，比如说，当一个应用需要一个类名为Abc.class.
 * 那么首先Application类加载器首先将加载这个类的请求传递给Extension类加载器，
 * 然后Extension类加载器会再次将加载请求传递给Bootstrap类加载器，
 * 这是Bootstrap类加载器会从JRE/lib/rt.jar目录下找Abc.class这个类文件，
 * 如果找到就执行byte code来执行程序。如果没有找到，那么将请求传递给Extension类加载器，
 * 如果Extension类加载器在JRE/lib/ext目录下没有找到Abc.class那么将请求传递给Application类加载器，
 * Application类加载器如果在classpath没有找到Abc.class那么就会抛出ClassNotFoundException异常。
 */
public class ClassLoaderTest {
    public static void main(String[] args) {
        try{
            System.out.println("classLoaderTest.getClass().getClassLoader() :" + ClassLoaderTest.class.getClassLoader());
        }catch (Exception e){
            Logger.getLogger(ClassLoaderTest.class.getName()).log(Level.SEVERE,"null",e);
        }
    }
}
