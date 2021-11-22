package com.wwj_concurrent.level3.juc.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author Julyan
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.level3.juc.semaphores
 * @Description:
 * @Date: 2021/11/22 14:24
 */
public class SimpleSemaphores {

    public static void main(String[] args) {
        SemaphoresLock lock = new SemaphoresLock();

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " begin running... ");
                    lock.lock();
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(Thread.currentThread().getName() + " get lock ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                    System.out.println(Thread.currentThread().getName() + " has unlock...");
                }
            }).start();
        }
    }

    static class SemaphoresLock {
        final static Semaphore semaphore = new Semaphore(1);

        protected void lock() {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        protected void unlock() {
            semaphore.release();
        }
    }

}
