package com.wwj_concurrent.level3.juc.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.level3.juc.cyclicbarrier
 * @Description: CyclicBarrier 只有等到几个线程同时执行await后才进行执行后面代码
 * @Date: 2021/11/12 10:32
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
            @Override
            public void run() {
                System.out.println("main Ended.");
            }
        });

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("T1 finished.");
                cyclicBarrier.await();
                System.out.println(" After T1 can do somethings.");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
                System.out.println("T2 finished.");
                cyclicBarrier.await();
                System.out.println(" After T2 can do somethings.");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
