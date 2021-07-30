package com.wwj_concurrent.leve1.chapter3;

import java.util.concurrent.TimeUnit;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/12/22 16:12
 * @Modified By：
 * This Monitor
 */
public class SynchronizedThis {
    public static void main(String[] args) {
        final ThisLock thisLock = new ThisLock();
        Thread t1 = new Thread(() -> {
            thisLock.m1();
        });

        Thread t2 = new Thread(() -> {
            thisLock.m2();
        });
        t1.start();
        t2.start();
    }
}

class ThisLock {
    private final Object LOCK = new Object();

    public void m1() {
        synchronized (LOCK) {
            System.out.println("This current Thread is " + Thread.currentThread().getName());
            try {
                TimeUnit.MILLISECONDS.sleep(10_000_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void m2() {
        synchronized (LOCK) {
            try {
                System.out.println("This current Thread is " + Thread.currentThread().getName());
                TimeUnit.MILLISECONDS.sleep(10_000_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
