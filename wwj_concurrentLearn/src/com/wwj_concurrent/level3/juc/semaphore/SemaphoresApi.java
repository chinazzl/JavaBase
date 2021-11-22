package com.wwj_concurrent.level3.juc.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author Julyan
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.level3.juc.semaphore
 * @Description:
 * @Date: 2021/11/22 15:47
 */
public class SemaphoresApi {

    /*
        1. semaphore.availablePermits() 监控可用的的凭据
        2. semaphore.getQueueLength() 监控阻塞的队列长度
     */
    public static void main(String[] args) throws InterruptedException {
        final Semaphore semaphore = new Semaphore(2);

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " in");
                    semaphore.acquire(2);
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println(Thread.currentThread().getName() + " get semaphore");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release(2);
                    System.out.println(Thread.currentThread().getName() + " out.");
                }
            }).start();
        }
        while (true) {
            System.out.println("AP => " + semaphore.availablePermits());
            System.out.println("QL => " + semaphore.getQueueLength());
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
