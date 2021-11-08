package com.wwj_concurrent.level3.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
     * @throws InterruptedException Stupid :
     *                              start counter times => 1636017317227
     *                              Counter result => 94048236
     *                              process spendTimes => 296
     *                              SyncCounter :
     *                              start counter times => 1636017427986
     *                              Counter result => 100000000
     *                              process spendTimes => 4396
     *                              LockCounter:
     *                              start counter times => 1636092888514
     *                              Counter result => 100000000
     *                              process spendTimes => 3466
     *                              AtomicCounter:
     *                              start counter times => 1636093826752
     *                              Counter result => 100000000
     *                              process spendTimes => 2431
     *                              UnsafeCounter:
     *                              start counter times => 1636100383138
     *                              Counter result => 100000000
     *                              process spendTimes => 6113
     */
    public static void main(String[] args) throws Exception {
//        CounterInterface counter = new StupidCounter();
//        CounterInterface counter = new SyncCounter();
//        CounterInterface counter = new LockCounter();
//        CounterInterface counter = new AtomicCounter();
        CounterInterface counter = new UnsafeCounter();
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

    /**
     * 使用 Unsafe创建 Counter类
     */
    static class UnsafeCounter implements CounterInterface {
        private volatile int counter = 0;

        private final long offset;

        private final Unsafe unsafe = getUnsafe();

        public UnsafeCounter() throws Exception {
            //定义一个偏移量，用于修改
            offset = unsafe.objectFieldOffset(UnsafeCounter.class.getDeclaredField("counter"));
        }

        @Override
        public void increment() {
            int current = counter;
            while (!unsafe.compareAndSwapInt(this, offset, current, current + 1)) {
                current = counter;
            }
        }

        @Override
        public int getCounter() {
            return counter;
        }
    }

    /**
     * 原子类型 Counter类
     */
    static class AtomicCounter implements CounterInterface {
        //        private AtomicInteger counter = new AtomicInteger();
        private AtomicLong counter = new AtomicLong();

        @Override
        public void increment() {
            counter.incrementAndGet();
        }

        @Override
        public int getCounter() {
            return (int) counter.get();
        }
    }

    /**
     * 使用Lock 锁 Counter 类
     */
    static class LockCounter implements CounterInterface {
        private int num = 0;

        private final Lock lock = new ReentrantLock();

        @Override
        public void increment() {
            try {
                lock.lock();
                num++;
            } finally {
                lock.unlock();
            }
        }

        @Override
        public int getCounter() {
            return num;
        }
    }


    /**
     * 同步锁 Counter类
     */
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

    /**
     * 普通Counter 类，有线程安全问题
     */
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

    static Unsafe getUnsafe() {
        Unsafe unsafe = null;
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return unsafe;
    }

}
