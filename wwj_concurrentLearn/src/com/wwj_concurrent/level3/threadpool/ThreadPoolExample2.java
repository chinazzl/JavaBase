package com.wwj_concurrent.level3.threadpool;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Julyan
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.level3.threadpool
 * @Description: newWorkStealingPool 根据电脑核数的一个可以自动销毁的线程池。
 * @Since: 1.8
 * @Date: 2021/12/21 15:31
 */
public class ThreadPoolExample2 {

    public static void main(String[] args) {
        useWorkStealThreadPool();
    }

    /**
     * 提供一个forJoinPool，以可用cpu核数进行创建线程，线程池会自动销毁。
     * <p>
     * new ForkJoinPool
     * (Runtime.getRuntime().availableProcessors(),
     * ForkJoinPool.defaultForkJoinWorkerThreadFactory,
     * null, true);
     */
    private static void useWorkStealThreadPool() {
        ForkJoinPool executorService = (ForkJoinPool) Executors.newWorkStealingPool();
        // 创建callable 等待线程池调用
        List<Callable<String>> callableList = IntStream.range(0, 20).boxed().map(i ->
                (Callable<String>) () -> {
                    System.out.println(" Task -" + Thread.currentThread().getName() + "-" + i);
                    sleep(2);
                    return "Task-" + i;
                }
        ).collect(Collectors.toList());
        System.out.println("======= waiting task finished =========");
        executorService.invokeAll(callableList).stream().map(future -> {
            try {
                return future.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).forEach(System.out::println);

    }

    private static void sleep(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
