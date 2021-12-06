package com.wwj_concurrent.leve2.chapter2.design.threadlocal;

import java.util.Random;

/**
 * @author zhang zhao lin
 * @date 2021年06月28日 23:15
 * @Description
 */
public class ThreadTest {

    private static final ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            threadLocal.set("Thread-1");
            System.out.println("Thread1 -> " + threadLocal.get());
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread1 = new Thread(() -> {
            threadLocal.set("Thread-2");
            System.out.println("Thread2 -> " + threadLocal.get());
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        thread1.start();
        System.out.println("main -> " + threadLocal.get());
        try {
            thread.join();
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
