package com.wwj_concurrent.leve1.chapter1.video;

import java.util.concurrent.TimeUnit;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/12/18 23:24
 * @Modified By：
 *  守护线程：伴随着主线程的销毁而销毁
 *
 */
public class DeamonThread {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("This is user Thread Terminate");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // 如果 setDaemon 在start之后 会报 java.lang.IllegalThreadStateException 错
        t.setDaemon(true);
        t.start();

        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("main Terminate");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
