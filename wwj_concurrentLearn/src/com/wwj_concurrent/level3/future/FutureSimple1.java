package com.wwj_concurrent.level3.future;

import java.util.concurrent.*;

/**
 * @author Julyan
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.level3.future
 * @Description: Future 学习
 * 1. future.get() 阻塞是阻塞哪个线程
 * 2. cancel 阻塞的是哪个线程
 * @Date: 2022/1/4 11:14
 */
public class FutureSimple1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
//        testFutureSimple();
        testFutureWithTimeOut();
    }

    /**
     * 认识Future。打断阻塞的线程是当前主线程。
     *
     * @throws ExecutionException   执行异常
     * @throws InterruptedException 当前线程阻塞被中断异常
     */
    private static void testFutureSimple() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future<Integer> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });
        Thread currentThread = Thread.currentThread();
        new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(3);
            } catch (InterruptedException e) {
            }
            currentThread.interrupt();
        }).start();

        System.out.println("========do Other things =========");
        Integer result = future.get();
        System.out.println("========finished end val is " + result + "=========");
    }

    /**
     * 测试 超时的future，抛出超时异常，<warning>但是线程并没有结束，而是继续进行执行</warning>
     *
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws TimeoutException
     */
    private static void testFutureWithTimeOut() throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(100);
                System.out.println("a hahahah");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });
        System.out.println("========do Other things =========");
        Integer result = future.get(3, TimeUnit.SECONDS);
        System.out.println("========finished end val is " + result + "=========");
    }
}
