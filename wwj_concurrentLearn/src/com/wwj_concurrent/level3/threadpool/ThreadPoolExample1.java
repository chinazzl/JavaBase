package com.wwj_concurrent.level3.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**********************************
 * @author zhang zhao lin
 * @date 2021年12月18日 19:23
 * @Description: 学习 newCachedThreadPool：一个可以一直创建线程的线程池,最大线程数小于Integer的界限值
 *                   newFixedThreadPool：一个有指定线程数量的线程池，核心线程数和最大线程数的数量是一致的,
 *                   newSimpleThreadPool：只允许一个核心线程进行创建
 **********************************/
public class ThreadPoolExample1 {

    public static void main(String[] args) {
        useNewCacheThreadPool();
    }

    /**
     * new ThreadPoolExecutor(nThreads, nThreads,
     * 0L, TimeUnit.MILLISECONDS,
     * new LinkedBlockingQueue<Runnable>()
     *
     * 一个
     */
    private static void useNewFixThreadPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
    }

    /**
     * These pools will typically improve the performance
     * of programs that execute many short-lived asynchronous tasks.
     * <p>
     * new ThreadPoolExecutor(0, Integer.MAX_VALUE,
     * <p>60L, TimeUnit.SECONDS,
     * new SynchronousQueue<Runnable>());
     * </p>
     * SynchronousQueue： 只允许一个任务进入队列
     * <p>
     * warning: 如果创建线程执行任务时间特别耗时，大于回收空闲线程时长，一旦超过Integer.Max_Value 则会报OOM
     */
    private static void useNewCacheThreadPool() {
        ThreadPoolExecutor cachedTheadPool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        IntStream.range(0, 100).boxed().forEach(i -> {
            cachedTheadPool.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(Thread.currentThread().getName() + "[" + i + "]");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });
        System.out.println("Active Thread => " + cachedTheadPool.getActiveCount());

    }
}
