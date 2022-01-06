package com.wwj_concurrent.level3.executeservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author Julyan
 * @version V1.0
 * @Description: 使用CompletionService 解决Future的一些局限性
 * @Date: 2022/1/6 15:58
 */
public class CompletionServiceAPI {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        completionServiceTake();
    }

    private static void completionServiceTake() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Callable<Integer>> callables = Arrays.asList(
                () -> {
                    sleep(20);
                    System.out.println("==== sleep 20 sec finished.");
                    return 20;
                },
                () -> {
                    sleep(10);
                    System.out.println("==== sleep 10 sec finished.");
                    return 10;
                }
        );
        ExecutorCompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);
        List<Future<Integer>> futures = new ArrayList<>();
        callables.forEach(callable -> futures.add(completionService.submit(callable)));
        Future<Integer> f;
        // take 方法是 阻塞的
        while ((f = completionService.take()) != null) {
            System.out.println(f.get());
        }
    }

    /**
     * sleep specify
     *
     * @param seconds 休眠毫秒值
     */
    private static void sleep(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
