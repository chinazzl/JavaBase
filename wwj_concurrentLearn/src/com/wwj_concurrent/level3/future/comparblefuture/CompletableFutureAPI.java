package com.wwj_concurrent.level3.future.comparblefuture;

import com.sun.xml.internal.ws.util.CompletedFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author Julyan
 * @version V1.0
 * @Description: CompletableFuture:
 * 1. Construct methods
 * 2. compose methods
 * 3. intermediate methods:
 * 3.1 intermediate the T
 * *   whenComplete {@link CompletableFutureAPI#testWhenCompleteAsync()}
 * *   whenCompleteAsync {@link CompletableFutureAPI#testWhenCompleteAsync()}
 * *   thenApply {@link CompletableFutureAPI#testApplyAsync()}
 * *   thenApplyAsync {@link CompletableFutureAPI#testApplyAsync()}
 * *   handleAsync
 * *   handle
 * *   toCompleteFutures
 * 3.2 intermediate the Void
 * *   thenAccept
 * *   thenAcceptAsync
 * *   thenRun
 * *   thenRunAsync
 * 4. terminate methods
 * @Date: 2022/1/13 9:46
 */
public class CompletableFutureAPI {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        Future<String> future = testWhenCompleteAsync();
//        Future<Integer> future = testApplyAsync();
        CompletableFuture<Integer> future = testHandleAsync();

        System.out.println("main Result => " + future.get());
        Thread.currentThread().join();

    }

    /**
     * whenComplete():
     * 1. 先执行前面的future，
     * 2. 获取结果然后执行whenComplete, v：代表future的数据；t：如果有异常情况将会抛出
     * whenCompleteAsync()：
     * 1. 先执行前面的future
     * 2. 将返回的future 调用whenCompleteAsync 异步执行
     *
     * @return
     */
    private static Future<String> testWhenCompleteAsync() {
        // ============ sync ==============
        CompletableFuture.supplyAsync(() -> "Hello world").whenComplete((v, t) -> {
            System.out.println(v + " sync finished. ");
        });

        // ============ Async ==============
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello world");
        future.whenCompleteAsync((v, t) -> {
            System.out.println("==== sleep Before");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("==== sleep has done. ");
        });
        return future;
    }

    /**
     * 将future返回的数据转换成另外的一个格式返回future
     * thenApplyAsync()具体返回 只是一个异步方法，最后返回的是最终的future，不会先返回future后执行theApplyAsync()
     *
     * @return
     */
    private static Future<Integer> testApplyAsync() {
        // ============ sync ==============
//        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> "Hello World").thenApply(data -> data.length());
        // ============ Async ==============
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello World");
        CompletableFuture<Integer> integerCompletableFuture1 = future.thenApplyAsync(data -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return data.length();
        });
        return integerCompletableFuture1;
    }

    /**
     * handleAsync BiFunction，
     * <p>
     * * 与 WhenComplete 区别：
     * 1. handle(BiFunction)，whenComplete(BiConsume)
     */
    private static CompletableFuture<Integer> testHandleAsync() {
        CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello World";
        });
        CompletableFuture<Integer> handle = supplyAsync.handle((data, t) -> {
            return data.length();
        });
        return handle;
    }
}

