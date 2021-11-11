package com.wwj_concurrent.level3.juc.coutdownlatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.level3.juc.coutdownlatch
 * @Description:
 * @Date: 2021/11/9 15:18
 */
public class CountDownLatch1 {

    private static final Random random = new Random(System.currentTimeMillis());

    private static final ExecutorService executor = Executors.newFixedThreadPool(2);

    //使用 CountDownLatch
    private static final CountDownLatch countDownLatch = new CountDownLatch(10);

    public static void main(String[] args) throws InterruptedException {
        int[] data = getData();
        for (int i = 0; i < data.length; i++) {
            executor.submit(new SimpleThread(data,i, countDownLatch));
        }
        countDownLatch.await();
        //当线程为空闲的时候 进行中断。
        executor.shutdown();
        //当所有的线程在规定时间都执行完后 再进行执行下面的逻辑
//        executor.awaitTermination(1, TimeUnit.HOURS);
        System.out.println("ALL work has done.");

    }

    /**
     * 简单的内部线程
     */
    static class SimpleThread implements Runnable {

        private int[] data;

        private int index;

        private CountDownLatch countDownLatch;

        public SimpleThread(int[] data, int index, CountDownLatch countDownLatch) {
            this.data = data;
            this.index = index;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int value = data[index];
            if (value % 2 == 0) {
                data[index] = value * 2;
            } else {
                data[index] = value;
            }
            System.out.println("Thread complete name is " + Thread.currentThread().getName() + " value is " + data[index]);
            countDownLatch.countDown();
        }
    }

    private static int[] getData() {
        return new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    }
}
