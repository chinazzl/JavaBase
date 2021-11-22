package com.wwj_concurrent.level3.juc.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author Julyan
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.level3.juc.semaphore
 * @Description:
 * @Date: 2021/11/22 16:38
 */
public class SemaphoresApi2 {

    public static void main(String[] args) throws InterruptedException {
        final Semaphore semaphore = new Semaphore(1);
        Thread t1 = new Thread(() -> {
            try {
                semaphore.acquire();
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
            System.out.println(Thread.currentThread().getName() + " finished.");
        });
        t1.start();
        TimeUnit.MILLISECONDS.sleep(50);

        Thread t2 = new Thread(() -> {
            try {
                semaphore.acquire();
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
            System.out.println(Thread.currentThread().getName() + " finished.");
        });
        t2.start();

        t2.interrupt();

    }
}
