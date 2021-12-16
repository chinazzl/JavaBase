package com.wwj_concurrent.level3.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**********************************
 * @author zhang zhao lin
 * @date 2021年12月16日 23:37
 * @Description: shutdown: 20个线程,10个核心线程为工作线程，10个线程在idle，调用shutdown()方法时，1. working的
 *                         10个线程工作，2. 10个线程被中断 3. 20个线程退出。 而导致无法及时全部中断，可以使用shutDownNow()或者waitAndTerminate方法。
 **********************************/
public class TheadPoolShutDown {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), r -> {
            Thread t = new Thread(r);
            return t;
        }, new ThreadPoolExecutor.AbortPolicy());

        IntStream.rangeClosed(0, 20).boxed().forEach(i -> {
            threadPoolExecutor.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(Thread.currentThread().getName() + "[" + i + "] will be done.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });
        try {
            threadPoolExecutor.shutdown();
            threadPoolExecutor.awaitTermination(1,TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("============Over================");
    }
}
