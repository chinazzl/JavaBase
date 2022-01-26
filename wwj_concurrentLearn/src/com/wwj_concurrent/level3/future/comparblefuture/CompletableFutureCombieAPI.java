package com.wwj_concurrent.level3.future.comparblefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author Julyan
 * @version V1.0
 * @Description: Combine API
 * 1. acceptBoth {@link CompletableFutureCombieAPI#testAcceptBoth()}
 * 2. AcceptEither {@link CompletableFutureCombieAPI#testAcceptEither()}
 * 3. RunAfterBoth {@link CompletableFutureCombieAPI#testRunAfterBoth()}
 * 4. RunAfterEither {@link CompletableFutureCombieAPI#testRunAfterEither()}
 * 5. ThenCombine {@link CompletableFutureCombieAPI#testCombine()}
 * 6. ThenCompose {@link CompletableFutureCombieAPI#testThenCompose()}
 * @Date: 2022/1/24 17:12
 */
public class CompletableFutureCombieAPI {

    public static void main(String[] args) throws InterruptedException {
//        testAcceptBoth();
//        testAcceptEither();
//        testRunAfterBoth();
//        testRunAfterEither();
//        testCombine();
        testThenCompose();
        Thread.currentThread().join();
    }

    /**
     * Compose 是block的,前一个Future执行完成后才能执行后面的Future,并且后面的Future参数是前一个Future的结果.
     */
    private static void testThenCompose() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("===== testThenCompose 1");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("====testThenCompose end 1 ");
            return "Hello World";
        }).thenCompose(dataStr -> CompletableFuture.supplyAsync(() -> {
            System.out.println("===== testThenCompose 2");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("====testThenCompose end 2 ");
            return dataStr.length();
        })).whenComplete((v, t) -> System.out.println(v + " === DONE."));
    }

    /**
     * Combine: 组合
     * 两个Future, 两个Future返回数据进行处理,并且处理完成逻辑
     */
    private static void testCombine() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("===== testThenCombine 1");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("====testThenCombine end 1 ");
            return "Hello World";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            System.out.println("===== testThenCombine 2");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("====testThenCombine end 2");
            return 1000;
        }), (d1, d2) -> d1.length() > d2).whenComplete((v, t) -> System.out.println(v + " === DONE"));
    }

    /**
     * RunAfterBoth 没有接收者的执行方法，只有一个future完成它就会执行后面的command
     * 不关心两个future输出
     */
    private static void testRunAfterEither() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("===== testRunAfterEither 1");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("====testRunAfterEither end 1 ");
            return "Hello World";
        }).runAfterBoth(CompletableFuture.supplyAsync(() -> {
            System.out.println("===== testRunAfterEither 2");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("====testRunAfterEither end 2 ");
            return "Hello World";
        }), () -> System.out.println("DONE"));
    }

    /**
     * RunAfterBoth 没有接收者的执行方法
     * 不关心两个future输出
     */
    private static void testRunAfterBoth() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("===== testRunAfterBoth 1");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("====testRunAfterBoth end 1 ");
            return "Hello World";
        }).runAfterBoth(CompletableFuture.supplyAsync(() -> {
            System.out.println("===== testRunAfterBoth 2");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("====testRunAfterBoth end 2 ");
            return "Hello World";
        }), () -> System.out.println("DONE"));
    }

    /**
     * AcceptEither: 两个Future 中 获取先执行的future,并切返回
     * 注意:
     * 1. acceptEither 必须两个future 必须返回值相同、
     * 2. 两个任务都会执行，注意资源的释放。
     */
    private static void testAcceptEither() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("===== testAcceptEither 1");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("====testAcceptEither end 1 ");
            return "Hello World";
        }).acceptEither(CompletableFuture.supplyAsync(() -> {
            System.out.println("===== testAcceptEither 2");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("====testAcceptEither end 2 ");
            return "either 2";
        }), System.out::println);
    }


    // AcceptBoth 同时执行两个Future
    private static void testAcceptBoth() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("===== testAcceptBoth 1");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("====testAcceptBoth end 1 ");
            return "Hello World";
        }).thenAcceptBoth(CompletableFuture.supplyAsync(() -> {
            System.out.println("===== testAcceptBoth 2");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("====testAcceptBoth end 2");
            return 1000;
        }), (d1, d2) -> System.out.println(d1 + " ---- " + d2));
    }
}
