package com.wwj_concurrent.level3.executeservice;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * @author Julyan
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.level3.executeservice
 * @Description: ExecutorService: invokeAll/invokeAny,sumit(Callable)
 * @Date: 2021/12/29 15:12
 */
public class ExecuteServiceAPI3 {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
//        testInvokeAny();
//        testInvokeAnyTimeOut();
//        testInvokeAllTimeOut();
//        testSubmitWithResult();
        testGetQueueAdd();
    }

    /**
     * 调用任意一个callable的一个结果并返回，其中 invokeAny方法是阻塞的。
     * {@link ExecutorService#invokeAny(Collection)}
     *
     * @throws ExecutionException   callable执行异常
     * @throws InterruptedException 阻塞打断异常
     */
    private static void testInvokeAny() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Callable<Integer>> callables = IntStream.range(0, 5).boxed().map(i -> (Callable<Integer>) () -> {
//            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(20));
            TimeUnit.SECONDS.sleep(5);

            return i;
        }).collect(toList());
        Integer invokeAny = executorService.invokeAny(callables);
        System.out.println(" invoke any: " + invokeAny);
    }

    /**
     * 测试调用超时，线程池中任务一直执行，直到达到超时时间后取一个结果值进行返回，其他的未执行完的线程进行cancel
     *
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws TimeoutException
     */
    private static void testInvokeAnyTimeOut() throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Integer invokeAny = executorService.invokeAny(IntStream.range(0, 5).boxed().map(i -> (Callable<Integer>) () -> {
//            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(2));
            TimeUnit.SECONDS.sleep(1);
            System.out.println("thread => " + Thread.currentThread().getName() + ": " + i);
            return i;
        }).collect(toList()), 5, TimeUnit.SECONDS);
        System.out.println("============finished==========");
        System.out.println(invokeAny);
    }


    /**
     * 执行线程池中所有的任务，也是阻塞
     *
     * @throws InterruptedException
     */
    private static void testInvokeAll() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Callable<Integer>> callables = IntStream.range(0, 5).boxed().map(i -> (Callable<Integer>) () -> {
//            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(20));
            return i;
        }).collect(toList());
        List<Future<Integer>> invokeAny = executorService.invokeAll(callables);
    }

    /**
     * 在指定超时时间内调用所有任务 (阻塞)
     *
     * @throws InterruptedException
     */
    private static void testInvokeAllTimeOut() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.invokeAll(IntStream.range(0, 5).boxed().map(i -> (Callable<Integer>) () -> {
                    TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                    return i;
                }).collect(toList()), 3, TimeUnit.SECONDS)
                .stream().map(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                }).forEach(System.out::println);
    }


    private static void testSubmit() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future<?> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Object NULL = future.get();
        System.out.println("task finished return " + NULL);
    }

    private static void testSubmitWithResult() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        String result = "DONE";
        Future<String> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, result);
        String resultVal = future.get();
        System.out.println("===finished result === " + resultVal);
    }

    /**
     * 测试直接获取{@link ThreadPoolExecutor#getQueue()} 直接进行添加是否能执行。
     */
    private static void testGetQueueAdd() {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        executorService.execute(() -> {
            System.out.println(Thread.currentThread().getName() + "- task");
        });
        executorService.getQueue().add(() -> System.out.println("Add " + Thread.currentThread().getName() + "- task"));
    }

}
