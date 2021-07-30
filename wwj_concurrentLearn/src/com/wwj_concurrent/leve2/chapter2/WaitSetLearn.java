package com.wwj_concurrent.leve2.chapter2;

import java.util.stream.IntStream;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2021/2/23 16:06
 * @Modified By：
 */
public class WaitSetLearn {

    private static final Object LOCK = new Object();

    public static void main(String[] args) {

        IntStream.rangeClosed(0, 10).forEach(i -> new Thread(String.valueOf(i)) {
            @Override
            public void run() {
                synchronized (LOCK) {
                    try {
                        System.out.println("The thread " + Thread.currentThread().getName() + " will coming  to wait");
                        LOCK.wait();
                        System.out.println("The thread " + Thread.currentThread().getName() + " leaved  to wait");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("out of synchrosize");

            }
        }.start());

        IntStream.rangeClosed(0, 10).forEach(i -> {
            synchronized (LOCK) {
                try {
                    LOCK.notify();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
