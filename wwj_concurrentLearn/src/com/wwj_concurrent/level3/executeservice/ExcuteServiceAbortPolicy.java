package com.wwj_concurrent.level3.executeservice;

import java.util.concurrent.*;

/**
 * @author Julyan
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.level3.executeservice
 * @Description: 四种拒绝策略:AbortPolicy: 超过最大线程数并且线程队列已经满了直接抛出异常
 * <pre>CallerRunsPolicy：</pre>
 * DiscardPolicy：什么都不做，也不抛出异常
 * DiscardOldestPolicy
 * @Date: 2021/12/27 16:24
 */
public class ExcuteServiceAbortPolicy {

    public static void main(String[] args) throws InterruptedException {
//        testAbortPolicy();
//        testDiscardPolicy();
//        testCallerRunsPolicy();
        testDiscardOldestPolicy();
    }

    /**
     * 使用AbortPolicy
     *
     * @throws InterruptedException
     */
    private static void testAbortPolicy() throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), r -> {
            Thread t = new Thread(r);
            return t;
        }, new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 3; i++) {
            executorService.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        TimeUnit.SECONDS.sleep(1);
        executorService.execute(() -> {
            System.out.println("extract threads. ");
        });
        System.out.println("=============================");
    }

    /**
     * 什么都不做，即不抛异常 也不做其他操作。
     *
     * @throws InterruptedException
     */
    private static void testDiscardPolicy() throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), r -> {
            Thread t = new Thread(r);
            return t;
        }, new ThreadPoolExecutor.DiscardPolicy());
        for (int i = 0; i < 3; i++) {
            executorService.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        TimeUnit.SECONDS.sleep(1);
        executorService.execute(() -> {
            System.out.println("extract threads. ");
        });
        System.out.println("=============================");
    }

    /**
     * 将超过最大线程并且占用满的额外线程交给当前线程进行处理（在这个为main线程）
     *
     * @throws InterruptedException
     */
    private static void testCallerRunsPolicy() throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), r -> {
            return new Thread(r);
        }, new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 3; i++) {
            executorService.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        TimeUnit.SECONDS.sleep(1);
        executorService.execute(() -> {
            System.out.println("extract threads. ");
            System.out.println("Thread => " + Thread.currentThread().getName() + " finished task. ");
        });
        System.out.println("=============================");
    }

    /**
     * 将最早的未完成的任务丢弃掉
     * @throws InterruptedException
     */
    private static void testDiscardOldestPolicy() throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), r -> {
            return new Thread(r);
        }, new ThreadPoolExecutor.DiscardOldestPolicy());
        for (int i = 0; i < 3; i++) {
            executorService.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println("OOOOOOOOOoo " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        TimeUnit.SECONDS.sleep(1);
        executorService.execute(() -> {
            System.out.println("extract threads. ");
        });
        System.out.println("=============================");
    }
}


