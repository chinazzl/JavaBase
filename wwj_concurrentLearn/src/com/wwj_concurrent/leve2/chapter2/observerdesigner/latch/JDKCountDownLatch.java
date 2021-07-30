package com.wwj_concurrent.leve2.chapter2.observerdesigner.latch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.leve2.chapter2.observerdesigner.latch
 * @Description:
 * @Date: 2021/7/7 11:05
 */
public class JDKCountDownLatch {

    private final static Random random = new Random(System.currentTimeMillis());

    private final static CountDownLatch countDownLatch = new CountDownLatch(5);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("多线程任务一");

        IntStream.rangeClosed(1, 5).forEach(i -> {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "正在执行");
                try {
                    Thread.sleep(random.nextInt(1000));
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        });
        countDownLatch.await();
        System.out.println("多线程任务执行完毕，执行第二阶段");
        System.out.println("do something...");
        System.out.println("Finished...");
    }
}
