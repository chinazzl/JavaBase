package com.wwj_concurrent.leve1.chapter4;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/9/16 10:57
 * @Modified By：
 * synchronized 关键字修饰同一个实例对象的不同方法
 */
public class ThisMonitor {

    public synchronized void method1() {
        System.out.println(currentThread().getName() + "enter to method1");
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用同步代码块方式，效果与method3是一样的
     */
    public void method2() {
        synchronized (this) {
            System.out.println(currentThread().getName() + "enter to method2");
            try {
                TimeUnit.MINUTES.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * method3 等价于 method2
     */
    public synchronized void method3() {
        System.out.println(currentThread().getName() + "enter to method3");
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ThisMonitor tm = new ThisMonitor();
        new Thread(tm::method1, "t1").start();
        new Thread(tm::method2, "t2").start();
    }
}
