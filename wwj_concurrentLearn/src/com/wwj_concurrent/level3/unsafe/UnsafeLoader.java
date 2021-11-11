package com.wwj_concurrent.level3.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

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
       /* 使用unsafe 进行 修改 属性，然后执行方法
       Worker worker = new Worker();
        Field field = worker.getClass().getDeclaredField("ACCESS_WORKER");
        unsafe.putInt(worker,unsafe.objectFieldOffset(field),50);
        worker.work();*/

        Simple simple = new Simple();
        System.out.println(sizeOf(simple));
    }

    private static long sizeOf(Object obj) {
        Unsafe unsafe = getUnsafe();
        Class<?> objClass = obj.getClass();
        Set<Field> fields = new HashSet<Field>();
        while (objClass != Object.class) {
            Field[] declaredFields = objClass.getDeclaredFields();
            for (Field field : declaredFields) {
                if ((field.getModifiers() & Modifier.STATIC) == 0) {
                    fields.add(field);
                }
            }
            objClass = objClass.getSuperclass();
        }
        long maxOffset = 0;
        for (Field field : fields) {
            //内存偏移量
            long fieldOffset = unsafe.objectFieldOffset(field);
            if (fieldOffset > maxOffset) {
                maxOffset = fieldOffset;
            }
        }

        return (maxOffset / 8 + 1) * 8;
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

        private long size;

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
