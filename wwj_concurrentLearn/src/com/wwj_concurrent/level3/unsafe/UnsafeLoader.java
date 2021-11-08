package com.wwj_concurrent.level3.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.level3.unsafe
 * @Description: Unsafe 进行 类的加载与实现
 * @Date: 2021/11/8 15:33
 */
public class UnsafeLoader {
    public static void main(String[] args) throws Exception {
       /* 类加载初始化
       Simple simple = Simple.class.newInstance();
        System.out.println(simple.get());*/
//   不进行初始化     Class<?> aClass = Class.forName("com.wwj_concurrent.level3.unsafe.UnsafeLoader$Simple");
        Unsafe unsafe = getUnsafe();
       /*  //不进行初始化
       Simple simple = (Simple) unsafe.allocateInstance(Simple.class);
        // run: 0
        System.out.println(simple.get());
        System.out.println(simple.getClass());
        System.out.println(simple.getClass().getClassLoader());*/
        Worker worker = new Worker();
        Field field = worker.getClass().getDeclaredField("ACCESS_WORKER");
        unsafe.putInt(worker,unsafe.objectFieldOffset(field),50);
        worker.work();
    }

    static class Worker {
        private int ACCESS_WORKER;

        protected void allowAccess() {
            ACCESS_WORKER = 40;
        }

        protected void work() {
            if (ACCESS_WORKER >= 40) {
                System.out.println("work could access work. ");
            }
        }
    }

    static class Simple {
        private int init;

        public Simple() {
            init = 1;
            System.out.println("==========");

        }

        public int get() {
            return init;
        }
    }

    static Unsafe getUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
