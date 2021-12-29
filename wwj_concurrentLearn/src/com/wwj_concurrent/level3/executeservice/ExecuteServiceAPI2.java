package com.wwj_concurrent.level3.executeservice;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**********************************
 * @author zhang zhao lin
 * @date 2021年12月27日 22:33
 * @Description: learn ExecutorService some Api
 * 1. coreSize：核心线程创建时机
 * 2. allowTimeout：允许核心线程空闲的时候进行回收
 * 3. preStartCoreThread：预启动核心线程
 * 4. ThreadPoolAdvice：执行thread会执行前置和后置方法
 * 5. remove：将线程从队列中进行移除
 **********************************/
public class ExecuteServiceAPI2 {
    public static void main(String[] args) throws InterruptedException {
//        testCoreThread();
//        testAllowTimeThread();
//        testRemoveThreadFromQueue();
//        testPreStartThread();
        testThreadPoolAdvice();
    }

    private static void testCoreThread() throws InterruptedException {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        // 创建之后执行初始化操作 设置核心线程为2，当提交任务会放到queue中，创建一个线程
        System.out.println(executorService.getActiveCount());
        // 提交一个任务 对照core中的数量 创建一个线程
        executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        TimeUnit.SECONDS.sleep(1);
        // 只创建一个
        System.out.println(executorService.getActiveCount());
    }

    /**
     * 允许核心线程最大空闲时间
     * 设置allowCoreThreadTimeOut的时候 {@link ThreadPoolExecutor#setKeepAliveTime(long, TimeUnit)} 不允许为0
     */
    private static void testAllowTimeThread() {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        executorService.setKeepAliveTime(10, TimeUnit.SECONDS);
        // 允许核心线程进行超时回收
        executorService.allowCoreThreadTimeOut(true);
        executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 将指定线程从thread Queue中进行剔除 不执行
     *
     * @throws InterruptedException
     */
    private static void testRemoveThreadFromQueue() throws InterruptedException {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        executorService.setKeepAliveTime(5, TimeUnit.SECONDS);
        // 允许核心线程进行超时回收
        executorService.allowCoreThreadTimeOut(true);
        IntStream.range(0, 2).boxed().forEach(i -> executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("execute task.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
        TimeUnit.MILLISECONDS.sleep(2);
        Runnable r = () -> {
            System.out.println(" I never do it.");
        };
        executorService.execute(r);
        executorService.remove(r);
    }

    /**
     * {@link ThreadPoolExecutor#prestartCoreThread} 启动一个核心线程，如果这个核心线程没有执行任务，则不开启新的线程
     */
    private static void testPreStartThread() {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        System.out.println(executorService.prestartCoreThread());
        System.out.println(executorService.getActiveCount());
        System.out.println(executorService.prestartCoreThread());
        System.out.println(executorService.getActiveCount());
        System.out.println(executorService.prestartCoreThread());
        System.out.println(executorService.getActiveCount());
    }

    private static void testThreadPoolAdvice() {
        ThreadPoolExecutor executorService = new MyThreadExecutor(2, 3, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), r -> {
            return new Thread(r);
        }, new ThreadPoolExecutor.AbortPolicy());
        IntStream.range(0, 2).boxed().forEach(i -> executorService.execute(new MyRunnable(i) {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    int a = 1/0;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }));
    }

    static abstract class MyRunnable implements Runnable {
        private int no;

        MyRunnable(int no) {
            this.no = no;
        }

        public int getData() {
            return this.no;
        }
    }

    // ===========Thread Pool Advice Begin ================
    static class MyThreadExecutor extends ThreadPoolExecutor {
        public MyThreadExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        }

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            System.out.println("init thread => " + ((MyRunnable) r).getData());
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            if (t == null) {
                System.out.println("After thread execute " + ((MyRunnable) r).getData());
            } else {
                t.printStackTrace();
            }
        }
    }
    // ===========Thread Pool Advice End ================
}
