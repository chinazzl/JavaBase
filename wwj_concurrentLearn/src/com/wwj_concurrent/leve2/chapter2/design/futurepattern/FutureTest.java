package com.wwj_concurrent.leve2.chapter2.design.futurepattern;


/**
 * @author zhang zhao lin
 * @date 2021年06月23日 23:09
 * @Description Future  -> 代表的是一个 返回的凭据
 *  FutureTask -> 代表和具体业务逻辑的隔离
 *  FutureService -> 代表 Future 和 FutureTask 的桥梁
 */
public class FutureTest {

    public static void main(String[] args) throws InterruptedException {
        FutureService<String> futureService = new FutureService<String>();
        Future<String> future = futureService.submit(() -> {
            /*Thread.sleep(1000L);
            return "FINISH";*/
           return get();
        });

        System.out.println("===========");
        Thread.sleep(1000L);
        System.out.println("do other things.");
        System.out.println(future.get());
    }

    private static String get() throws InterruptedException {
        Thread.sleep(1000L);
        return "FINISH";
    }
}
