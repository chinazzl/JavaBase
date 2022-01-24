package com.wwj_concurrent.level3.future.comparblefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author Julyan
 * @version V1.0
 * @Description:
 * @Date: 2022/1/24 17:12
 */
public class CompletableFutureCombieAPI {

    public static void main(String[] args) throws InterruptedException {
        testAcceptBoth();
        Thread.currentThread().join();
    }

    // TODO
    private static void testAcceptBoth() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("===== testAcceptBoth");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("====testAcceptBoth end ");
            return "Hello World";
        }).thenAccept(s -> System.out.println(s.length()));
    }

}
