package com.wwj_concurrent.leve1.chapter4;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/9/16 11:15
 * @Modified By：
 * 一个类下使用sync进行修饰不同的静态方法
 */
public class ThisClassMonitor {

    public synchronized static void method1() {
        System.out.println(currentThread().getName() + " enter to method1");
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized static void method2() {
        System.out.println(currentThread() + " enter to method2");
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void method3() {
        synchronized (ThisClassMonitor.class) {
            System.out.println(currentThread() + " enter to method2");
            try {
                TimeUnit.MINUTES.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(ThisClassMonitor::method1,"t1").start();
        new Thread(ThisClassMonitor::method2,"t2").start();
    }
}
