package com.wwj_concurrent.level3.juc.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author Julyan
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.level3.juc.semaphore
 * @Description: drainPermits：将所有的 信号量进行消费,
 *               hasQueuedThreads: 是否有阻塞的线程进入队列
 *               tryAcquire(timeout,timeunit)：尝试进行在指定超时时间进行获取，如果没有 获取则继续进行，返回false
 * @Date: 2021/11/23 14:08
 */
public class SemaphoresApi3 {
    public static void main(String[] args) throws InterruptedException {
        final Semaphore semaphore = new Semaphore(2);
        Thread t1 = new Thread(() -> {
            try {
                semaphore.drainPermits();
                System.out.println(semaphore.availablePermits());
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release(2);
            }
            System.out.println("T1 finished.");
        });
        t1.start();
        TimeUnit.SECONDS.sleep(2);
        Thread t2 = new Thread(() -> {
            try {
                // 尝试拿信号量，如果获取不到就block住
//                semaphore.acquire();
                // semaphore 尝试取信号量，如果超过超时时间，则返回一个boolean，不阻塞
                System.out.println(semaphore.tryAcquire(2, TimeUnit.SECONDS) ? "working" : "none accquire");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
            System.out.println("T2 finished.");
        });
        t2.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(semaphore.hasQueuedThreads());
    }
}
