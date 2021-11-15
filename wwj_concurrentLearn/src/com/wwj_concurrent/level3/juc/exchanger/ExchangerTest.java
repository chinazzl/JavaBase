package com.wwj_concurrent.level3.juc.exchanger;

import java.sql.Time;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.level3.juc.exchanger
 * @Description: Exchanger 线程对进行数据交换。
 * @Date: 2021/11/15 10:36
 */
public class ExchangerTest {

    public static void main(String[] args) {
        final Exchanger<String> exchanger = new Exchanger<String>();
        new Thread(() -> {
            try {
                String exchange = exchanger.exchange("The thread is T-A, please changed it.", 20, TimeUnit.SECONDS);
                System.out.println(Thread.currentThread().getName() + " worked exchange , The value is " + exchange);
            } catch (InterruptedException | TimeoutException e) {
                e.printStackTrace();
                System.out.println("exchanged TimeOut...");
            }
        }, "T-A").start();

        new Thread(() -> {
            try {
                String result = exchanger.exchange("The thread is T-B, please changed it.");
                System.out.println(Thread.currentThread().getName() + " worked exchange , The value is " + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T-B").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                String result = exchanger.exchange("The thread is T-C, please changed it.");
                System.out.println(Thread.currentThread().getName() + " worked exchange , The value is " + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T-C").start();

    }


}
