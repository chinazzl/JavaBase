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
//        Future<String> future = testCompleteAsync("Hello World");
//        Future<?> future = testAnyOf();
//        System.out.println("$$$%%%%" + future.get());
        testAllOf();
        System.out.println("====== main do things");
        Thread.currentThread().join();
    }

    /**
     * 异步执行AllOf()所有任务，然后 全部执行完毕后再进行返回
     */
    private static void testAllOf() {
        CompletableFuture.allOf(CompletableFuture.runAsync(() -> {
                            try {
                                System.out.println("=== 1 start sleep");
                                TimeUnit.SECONDS.sleep(5);
                                System.out.println("=== 1 end sleep");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }).whenComplete((v, t) -> System.out.println("=== 1 Over===")),
                        CompletableFuture.supplyAsync(() -> {
                            try {
                                System.out.println("=== 2 start sleep");
                                TimeUnit.SECONDS.sleep(4);
                                System.out.println("=== 2 end sleep");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return "step 2";
                        }).whenComplete((v, t) -> System.out.println(v + " finished ===")))

                .whenComplete((v, t) -> System.out.println("total：finished All "));
    }

    /**
     * anyOf()会任意返回一个runnable的执行结果，不过其他的task会同事进行。
     * 注意：以防有游离线程和僵尸线程出现，导致运行时间一场会程序崩溃。
     *
     * @return
     */
    private static Future<?> testAnyOf() {
        return CompletableFuture.anyOf(CompletableFuture.runAsync(() -> {
                    try {
                        System.out.println("=== start sleep");
                        TimeUnit.SECONDS.sleep(5);
                        System.out.println("=== end sleep");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).whenComplete((v, t) -> System.out.println("====over ====")),
                CompletableFuture.supplyAsync(() -> {
                    try {
                        System.out.println("=== start sleep");
                        TimeUnit.SECONDS.sleep(4);
                        System.out.println("=== end sleep");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "Hello";
                }).whenComplete((v, t) -> System.out.println(v + "==== over ===")));
    }

    /**
     * 对 前面的代码执行成功返回数据后 进行执行future
     *
     * @param data
     * @return
     */
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
