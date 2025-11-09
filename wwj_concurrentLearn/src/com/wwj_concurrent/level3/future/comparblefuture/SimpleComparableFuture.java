package com.wwj_concurrent.level3.future.comparblefuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**********************************
 * @author zhang zhao lin
 * @date 2022年01月10日 22:19
 * @Description: CompletableFuture: 自己管理Future 和 Executor，并且将使用的Executor设置为守护线程。
 * 摆脱future的一些局限性，
 *  1. 执行get() 会导致block。
 *  2. 批量执行callable的时候，会使执行快的还在block，影响性能。
 *  3. 如果get()后的数据再次执行call/run 的时候需要重新拆概念佳能Callable/Runnable。
 **********************************/
public class SimpleComparableFuture {
    public static void main(String[] args) throws InterruptedException {

        /*ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Callable<Integer>> callables = IntStream.range(0, 10).boxed()
                .map(i -> (Callable<Integer>) SimpleComparableFuture::get).collect(Collectors.toList());
        executorService.invokeAll(callables).stream().map(future -> {
            try {
                return future.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).forEach(SimpleComparableFuture::display);*/
        // 使用CompletableFuture
        //IntStream.range(0, 10).boxed().forEach(i -> CompletableFuture.supplyAsync(SimpleComparableFuture::get)
        //        .thenAccept(SimpleComparableFuture::display)
        //        .whenComplete((v, t) -> System.out.println(i + " be done")));
        List<Integer> integers = testFultureEx();
        System.out.println(integers);


        Thread.currentThread().join();
    }

    private static void display(int data) {
        int value = ThreadLocalRandom.current().nextInt(20);
        try {
            System.out.println(Thread.currentThread().getName() + " display execute task " + value);
            TimeUnit.SECONDS.sleep(value);
            System.out.println(Thread.currentThread().getName() + " display finished done " + data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * simple CompletableFuture
     */
    private static void simpleComparableFuture() {
        CompletableFuture.runAsync(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                })
                // 当完成的时候调用，v: 执行过程产生的result；t: 执行过程中产生的异常
                .whenComplete((v, t) -> {
                    System.out.println(" I will done");
                });
        // 由于CompletableFuture 中的Executor是守护线程 导致当前的主线程执行后 直接推出
        System.out.println("continue other things");
    }

    /**
     * 相当于 访问网络资源
     *
     * @return
     */
    private static int get() {
        int value = ThreadLocalRandom.current().nextInt(20);
        try {
            System.out.println(Thread.currentThread().getName() + " get execute task " + value);
            TimeUnit.SECONDS.sleep(value);
            System.out.println(Thread.currentThread().getName() + " get finished done " + value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value;
    }

    private static List<Integer> testFultureEx() {
        List<Integer> result = new ArrayList<>();

        List<CompletableFuture<Integer>> list = new ArrayList<>();
        IntStream.rangeClosed(0, 3).forEach(value -> {
            CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(SimpleComparableFuture::get);
            list.add(integerCompletableFuture);
        });

        list.forEach(f -> f.whenComplete((v, t) -> {
            if (t == null) {
                result.add(v);
            }else  {
                System.out.println(t.getMessage());
            }
        }));
        System.out.println(result);
        //for (CompletableFuture<Integer> integerCompletableFuture : list) {
        //    try {
        //        Integer i = integerCompletableFuture.get(5, TimeUnit.SECONDS);
        //        result.add(i);
        //    }catch (Exception e){
        //        System.out.println("future执行失败");
        //    }
        //}
        return result;
    }
}
