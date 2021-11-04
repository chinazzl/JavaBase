package com.wwj_concurrent.level3.unsafe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.level3.unsafe
 * @Description:
 * @Date: 2021/11/4 16:35
 */
public class CompareUnsafeCounter {

    private static final ExecutorService exec = Executors.newFixedThreadPool(1000);

    /**
     * @throws InterruptedException
     * Stupid :
     *  start counter times => 1636017317227
     *  Counter result => 94048236
     *  process spendTimes => 296
     *SyncCounter
     *  start counter times => 1636017427986
     *  Counter result => 100000000
     *  process spendTimes => 4396
     *
     */
    public static void main(String[] args) throws InterruptedException {
//        CounterInterface counter = new StupidCounter();
        CounterInterface counter = new SyncCounter();
        long startTime = System.currentTimeMillis();
        System.out.println("start counter times => " + startTime);
        //创建1000个任务 异步执行
        for (int i = 0; i < 1000; i++) {
            exec.submit(new CounterRunnable(counter, 100000));
        }
        exec.shutdown();
        exec.awaitTermination(1, TimeUnit.HOURS);
        long endTime = System.currentTimeMillis();
        System.out.println("Counter result => " + counter.getCounter());
        System.out.println("process spendTimes => " + (endTime - startTime));
    }

    interface CounterInterface {
        void increment();

        int getCounter();
    }

    static class SyncCounter implements CounterInterface {
        private int num = 0;

        @Override
        public synchronized void increment() {
            num++;
        }

        @Override
        public int getCounter() {
            return num;
        }
    }

    static class StupidCounter implements CounterInterface {
        private int num = 0;

        @Override
        public void increment() {
            num++;
        }

        @Override
        public int getCounter() {
            return num;
        }
    }

    static class CounterRunnable implements Runnable {
        private CounterInterface counterInterface;

        private int num;

        public CounterRunnable(CounterInterface counterInterface, int num) {
            this.counterInterface = counterInterface;
            this.num = num;
        }

        @Override
        public void run() {
            for (int i = 0; i < num; i++) {
                counterInterface.increment();
            }
        }
    }

}
