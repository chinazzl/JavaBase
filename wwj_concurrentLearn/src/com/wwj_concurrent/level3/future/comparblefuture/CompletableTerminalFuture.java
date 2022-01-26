package com.wwj_concurrent.level3.future.comparblefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author Julyan
 * @version V1.0
 * @Description: CompletableFuture Terminal 操作，返回 Boolean or String
 * @Date: 2022/1/26 9:15
 */
public class CompletableTerminalFuture {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        testGetNow();
//        testComplete();
//        testJoin();
//        testExceptional();
//        testObtrudeException();
        CompletableFuture<String> future = testErrorHandle();
        future.whenComplete((v,t) -> System.out.println(v + " future has done"));
        Thread.currentThread().join();
    }

    /**
     * 可以对相同的future进行不同处理，
     *
     * @return
     */
    private static CompletableFuture<String> testErrorHandle() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            sleep(3);
            System.out.println("=====================test complete");
            return "Hello ";
        });

        future.thenApply(s -> {
            Integer.parseInt(s);
            sleep(5);
            System.out.println(s + " ==== future keep Moving ====");
            return " world";
        }).exceptionally(Throwable::getMessage).thenAccept(System.out::println);
        return future;
    }


    /**
     * future 调用obtrudeException 直接放弃future的执行 直接抛出异常。
     * 场景：抢占机制，如果一个线程执行future处理后，另一个线程执行直接抛出异常。
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void testObtrudeException() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
//            sleep(5);
            System.out.println("=====================test complete");
            return "Hello world";
        });
        future.obtrudeException(new Exception("i am error"));
        System.out.println(future.get());
    }

    /**
     * 如果future在调用completeExceptionally时没有完成，则直接抛出一个异常
     * 如果在调用completeExceptionally 之前future已经有值则返回false，
     * 如果在调用completeExceptionally 之前future还没有执行完，则返回true，并且抛出异常。
     */
    private static void testExceptional() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
//            sleep(5);
            System.out.println("=====================test complete");
            return "Hello world";
        });
        sleep(1);
        boolean b = future.completeExceptionally(new RuntimeException("get data TimeOutException..."));
        System.out.println(b);
        System.out.println(future.get());
    }

    /**
     * 和get()方法一样，只不过如果出现异常的情况下才进行抛出异常，否则不抛出异常。
     */
    private static void testJoin() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            sleep(5);
            System.out.println("=====================test complete");
            return "Hello world";
        });
        future.join();
    }

    /**
     * 调用complete方法，返回boolean，
     * 如果返回为true，则表示future转换complete的状体，并且返回的是设置的值。
     * 如果返回false，则表示没有转换成complete的状态，返回的是future的实际值。
     * <p>
     * 如果调用complete() 先于执行CompletableFuture调用，则直接被cancel掉，
     * 如果CompletableFuture已经执行中，则不cancel掉
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void testComplete() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            sleep(5);
            System.out.println("=====================test complete");
            return "Hello world";
        });
        // CompletableFuture已经执行中，则不cancel掉
        sleep(1);
        boolean new_world = future.complete("NEW World");
        System.out.println(new_world);
        System.out.println(future.get());
    }

    /**
     * getNow() 只获取当前设置的数据，future的数据正常执行
     */
    private static void testGetNow() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
//            sleep(5);
            System.out.println("test get Now()");
            return "Hello world";
        });
        String new_world = future.getNow("NEW WORLD");
        System.out.println(new_world);
        System.out.println(future.get());
    }

    /**
     * sleep specify seconds.
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
