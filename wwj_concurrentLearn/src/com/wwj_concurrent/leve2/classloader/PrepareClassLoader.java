package com.wwj_concurrent.leve2.classloader;

import java.util.Random;

/**********************************
 * @author zhang zhao lin
 * @date 2021年08月19日 22:09
 * @Description 类的主动与被动加载
 **********************************/
public class PrepareClassLoader {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {

        //子类访问父类的变量 无法初始化子类
//        System.out.println(SubObj.str);
        // 定义引用数组 不会初始化 加载类
//        Obj[] objs = new Obj[10];
        //编译期直接算出来的 编译期间会放到常量池中，无需进行类初始化
//        System.out.println(Obj.salary);
        //final 修饰的引用常量 在运行期进行加载
//        System.out.println(Obj.x);

//        System.out.println(System.getProperty("java.class.path"));
        Class<?> aClass = Class.forName("com.wwj_concurrent.leve2.classloader.PrepareClassLoader$ObjectInterface");
        System.out.println(aClass.getMethod("test",Void.class));
    }

    static class Obj {

        public static String str = "parentClass";

        public final static long salary = 100000;

        public final static int x = new Random().nextInt(100);

        static {
            System.out.println("obj 初始化。。。");
        }
    }

    static class SubObj extends Obj implements ObjectInterface {

        static {
            System.out.println("子类初始化");
        }

        @Override
        public void test() {
            System.out.println("aaa");
        }
    }

    interface ObjectInterface {

        void test();
    }
}


