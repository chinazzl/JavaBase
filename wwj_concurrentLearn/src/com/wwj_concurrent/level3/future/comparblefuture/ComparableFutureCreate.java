package com.wwj_concurrent.level3.future.comparblefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**********************************
 * @author zhang zhao lin
 * @date 2022年01月11日 22:13
 * @Description: CompletableFuture:
 *  1. supplyAsync
 *  2. anyOf()
 *  3. allOf()
 **********************************/
public class ComparableFutureCreate {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        testSupplyAsync();
//        Future<?> future = testRunAsync();
        Future<String> future = testCompleteAsync("Hello World");
        System.out.println(future.get());
        System.out.println("====== main do things");
        Thread.currentThread().join();
    }

    private static void testAllOf() {
        // TODO
    }

    private static void testAnyOf() {
        // TODO
    }

    private static Future<String> testCompleteAsync(String data) {
        return CompletableFuture.completedFuture(data).whenComplete((v, t) -> System.out.println(v + " complete"));
    }

    /**
     * 相当于submit，可以注册一个回调函数
     */
    private static Future<?> testRunAsync() {
        return CompletableFuture.runAsync(() -> {
            try {
                System.out.println("=== start sleep");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("=== end sleep");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).whenComplete((v, t) -> System.out.println("====over==="));
    }

    /**
     * SupplyAsync进行异步执行任务，等待任务执行后返回数据。
     */
    private static void testSupplyAsync() {
        CompletableFuture.supplyAsync(Object::new)
                .thenAcceptAsync(o -> {
                    try {
                        System.out.println("=== start sleep");
                        TimeUnit.SECONDS.sleep(5);
                        System.out.println(o + "=== end sleep");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                })
                // 进行级联
                .runAfterBoth(CompletableFuture.supplyAsync(() -> "Hello")
                        .thenAcceptAsync(s -> {
                            try {
                                System.out.println("=== 2 start sleep");
                                TimeUnit.SECONDS.sleep(3);
                                System.out.println(s + "=== 2 end sleep");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }), () -> System.out.println("=== finished==="));
    }
}
