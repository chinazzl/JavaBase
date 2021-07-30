package com.wwj_concurrent.leve1.chapter2.stopThread;

import java.util.concurrent.TimeUnit;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/7/24 11:46
 * @Modified By：
 */
public class CatchInteruptSignal {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(){
            @Override
            public void run() {
                System.out.println("The Work is Start");
                while(!isInterrupted()) {

                }
                System.out.println("I will exists working");
            }
        };
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(" THread will be shutDown");
        thread.interrupt();
    }
}
