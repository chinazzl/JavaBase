package com.wwj_concurrent.level3.future.comparblefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author Julyan
 * @version V1.0
 * @Description: runAsync, anyOf, supplyAsync
 * @Date: 2022/1/11 16:31
 */
public class SimpleComparableFutureCreate {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println(testSupplyAsync().get());

        Thread.currentThread().join();
    }

    private static Future<?> testSupplyAsync() {
        return CompletableFuture.supplyAsync(Object::new).thenAccept(o -> {
            try {
                System.out.println("1 === 1 start");
                TimeUnit.SECONDS.sleep(5);
                System.out.println(o + "=== 1 end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).runAfterBoth(CompletableFuture.supplyAsync(() -> "Hello")
                .thenAccept(s -> {
                    try {
                        System.out.println("2 === 2 start");
                        TimeUnit.SECONDS.sleep(3);
                        System.out.println(s + "=== 2 end");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }), () -> System.out.println("===Finished==="));
    }

}
