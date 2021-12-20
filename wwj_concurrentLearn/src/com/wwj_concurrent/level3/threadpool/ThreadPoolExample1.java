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
//        useNewCacheThreadPool();
        useNewSingleThreadPool();
    }

    /**
     * singleThreadExecutor 和 普通Thread创建的区别：
     * 1. 普通Thread执行后直接销毁，singleThreadExecutor 执行后不销毁，有一个核心线程
     * 2. Thread执行多个线程不放到Queue中，singleThreadExecutor 执行多个任务，会将他们放到Queue中。
     * <p>
     * new FinalizableDelegatedExecutorService
     * (new ThreadPoolExecutor(1, 1,
     * 0L, TimeUnit.MILLISECONDS,
     * new LinkedBlockingQueue<Runnable>()));
     * <p>
     * 等价
     * <p>
     * new ThreadPoolExecutor(nThreads, nThreads,
     * 0L, TimeUnit.MILLISECONDS,
     * new LinkedBlockingQueue<Runnable>()
     * ================================
     * Executors.newFixedThreadPool(1);
     */
    private static void useNewSingleThreadPool() {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        IntStream.range(0, 10).boxed().forEach(i -> {
            singleThreadExecutor.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(Thread.currentThread().getName() + "[" + i + "]");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });
    }

    /**
     * new ThreadPoolExecutor(nThreads, nThreads,
     * 0L, TimeUnit.MILLISECONDS,
     * new LinkedBlockingQueue<Runnable>()
     * <p>
     * 一个具有固定线程的线程池
     */
    private static void useNewFixThreadPool() throws InterruptedException {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        IntStream.range(0, 10).boxed().forEach(i -> {
            executorService.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(Thread.currentThread().getName() + "[" + i + "]");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Active Thread => " + executorService.getActiveCount());
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
