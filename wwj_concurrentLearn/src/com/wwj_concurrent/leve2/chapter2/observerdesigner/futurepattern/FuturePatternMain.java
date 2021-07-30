package com.wwj_concurrent.leve2.chapter2.observerdesigner.futurepattern;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.leve2.chapter2.observerdesigner.futurepattern
 * @Description:
 * @Date: 2021/6/29 11:08
 */
public class FuturePatternMain {
    public static void main(String[] args) {
        FutureService<String> futureService = new FutureService<String>();
        Thread thread = new Thread(() -> {
            Future<String> submit = futureService.submit(() -> {
                try {
                    Thread.sleep(10000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "Finished";
            });
            System.out.println(submit.get());
        });
        thread.start();
        System.out.println("=====================");

        System.out.println("do other things...");


    }
}
