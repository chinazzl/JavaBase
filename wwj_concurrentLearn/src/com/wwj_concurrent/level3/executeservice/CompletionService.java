package com.wwj_concurrent.level3.executeservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author Julyan
 * @version V1.0
 * @Description: 补全Future的缺陷
 * @Date: 2022/1/5 16:55
 */
public class CompletionService {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        futureLimit2();
    }

    /**
     * no callback function
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void futureLimit() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });
        future.get();
        System.out.println("======================");
    }

    /**
     * group future
     * 有的任务执行快 无法继续指定任务结果的相应的后续操作 性能出现异常。
     * 解决：jdk1.8- 将每个任务进行拆分分别执行，然后添加到 List<Future> 中（即执行快的一定在集合的前几个元素），然后遍历获取
     *
     * @throws InterruptedException
     */
    private static void futureLimit2() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Callable<Integer>> callables = Arrays.asList(
                () -> {
                    sleep(10);
                    System.out.println("====== callable 1 ========");
                    return 10;
                },
                () -> {
                    sleep(20);
                    System.out.println("====== callable 2 ========");

                    return 20;
                }
        );
  /*      List<Future<Integer>> futures = executorService.invokeAll(callables);

        Integer f1 = futures.get(0).get();
        // 第一个耗时短返回过后还是阻塞状态 耗费性能
        System.out.println(f1);
        Integer f2 = futures.get(1).get();
        System.out.println(f2);*/
        /*
            解决：jdk1.8- 将每个任务进行拆分分别执行，然后添加到 List<Future> 中（即执行快的一定在集合的前几个元素），然后遍历获取
         */
        List<Future<Integer>> futures = new ArrayList<>();
        Future<Integer> future1 = executorService.submit(callables.get(1));
        Future<Integer> future2 = executorService.submit(callables.get(0));
        futures.add(future1);
        futures.add(future2);
        for (Future<Integer> future : futures) {
            Integer integer = future.get();
            System.out.println(integer + " do somethings...");
        }

    }

    /**
     * sleep by specify
     *
     * @param seconds
     */
    private static void sleep(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
