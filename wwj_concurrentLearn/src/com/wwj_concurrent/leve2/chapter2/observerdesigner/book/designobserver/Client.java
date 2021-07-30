package com.wwj_concurrent.leve2.chapter2.observerdesigner.book.designobserver;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.leve2.chapter2.observerdesigner.book.designobserver
 * @Description:
 * @Date: 2021/6/22 10:30
 */
public class Client {
    public static void main(String[] args) {
        ThreadObserverable observer = new ThreadObserverable(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Thread is DONE");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
        observer.start();
    }
}
