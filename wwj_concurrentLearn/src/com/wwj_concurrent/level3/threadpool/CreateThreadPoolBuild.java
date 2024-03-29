package com.wwj_concurrent.level3.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**********************************
 * @author zhang zhao lin
 * @date 2021年12月15日 22:00
 * @Description
 **********************************/
public class CreateThreadPoolBuild {

    public static void main(String[] args) {
        ThreadPoolExecutor executorBuilder = createThreadPoolExecutorBuilder();

        int activeSize = -1;
        int queueSize = -1;
        while (true) {
            if (activeSize != executorBuilder.getActiveCount() || queueSize != executorBuilder.getQueue().size()) {
                System.out.println("activeCount => " + executorBuilder.getActiveCount());
                System.out.println("CoreSize => " + executorBuilder.getCorePoolSize());
                System.out.println("QueueSize => " + executorBuilder.getQueue().size());
                System.out.println("MaxSize => " + executorBuilder.getMaximumPoolSize());
                System.out.println("PoolSize => " + executorBuilder.getPoolSize());
                activeSize = executorBuilder.getActiveCount();
                queueSize = executorBuilder.getQueue().size();
                System.out.println("==================================");
            }
        }
    }

    private static ThreadPoolExecutor createThreadPoolExecutorBuilder() {

        /**
         * int corePoolSize, 在线程池中一直保持一定量的线程，即使他们是空闲状态。除非设置 allowCoreThreadTimeOut进行回收
         * int maximumPoolSize, 线程池中最多存放的线程个数
         * long keepAliveTime, 当线程的数量超过core线程数量，则超过最大时间后将空闲的线程给终止掉
         * TimeUnit unit, 时间单元
         * BlockingQueue<Runnable> workQueue, 在线程池使执行runnable之前，将线程放到workQueue中。
         * ThreadFactory threadFactory,
         * RejectedExecutionHandler handler 拒绝策略
         *
         */
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 4, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(5), r -> {
            Thread t = new Thread(r);
            t.setName("my-pool-executor");
            return t;
        }, new ThreadPoolExecutor.AbortPolicy());
        threadPoolExecutor.submit(() -> sleepSeconds(10));
        threadPoolExecutor.submit(() -> sleepSeconds(10));
        /*
            1. 当线程池提交任务少于核心线程池的时候，会创建一个新的线程
            2. 当核心线程满了，再向线程池提交任务 且小于最大线程数，队列已经满了的时候会从队列中取出一个任务进行创建一个新的线程
            3. 如果队列中的任务数已经满了，并且活动的任务数大于最大线程数 则调用拒绝策略
         */
        threadPoolExecutor.submit(() -> sleepSeconds(10));
        threadPoolExecutor.submit(() -> sleepSeconds(10));
        // Task has full in thread pool. queue had full .
        threadPoolExecutor.submit(() -> sleepSeconds(10));
        // Task rejected from this thread pool
        //threadPoolExecutor.submit(() -> sleepSeconds(10));

        return threadPoolExecutor;
    }

    private static void sleepSeconds(long seconds) {
        try {
            System.out.println("* " + Thread.currentThread().getName() + " * sleep " + seconds);
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
