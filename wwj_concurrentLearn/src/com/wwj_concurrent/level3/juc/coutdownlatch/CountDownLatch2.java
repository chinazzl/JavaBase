package com.wwj_concurrent.level3.juc.coutdownlatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.level3.juc.coutdownlatch
 * @Description:
 * @Date: 2021/11/9 16:56
 */
public class CountDownLatch2 {

    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        new Thread(() -> {
            try {
                Thread.sleep(random.nextInt(2000));
                countDownLatch.await();
                System.out.println(" after doSomething...  ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(random.nextInt(2000));
                System.out.println("will prepare data something.... ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(random.nextInt(2000));
                countDownLatch.await();
                System.out.println(" hahhhhhhh");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.currentThread().join();
        System.out.println("Thread Name " + Thread.currentThread().getName());
    }
}
