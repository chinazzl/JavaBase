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
        testInvokeAnyTimeOut();
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
     * 测试调用超时
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

}
