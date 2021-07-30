package com.wwj_concurrent.leve1.chapter3;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/12/22 22:25
 * @Modified By：
 * 静态同步
 */
public class SynchronizedStatic {

    static {
        synchronized (SynchronizedStatic.class) {
            try {
                System.out.println(Thread.currentThread().getName() + "==> static thread sync");
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized static void m1() {
        try {
            System.out.println(Thread.currentThread().getName() + "==> m1");
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized static void m2() {
        try {
            System.out.println(Thread.currentThread().getName() + "==> m2");
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void m3() {
        try {
            System.out.println(Thread.currentThread().getName() + "==> m3");
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

