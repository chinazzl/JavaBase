package com.wwj_concurrent.level3.juc.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Julyan
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.level3.juc.exchanger
 * @Description:
 * @Date: 2021/11/15 16:48
 */
public class ExchangerTest2 {

    public static void main(String[] args) {
        final Exchanger<Integer> exchanger = new Exchanger<>();
        new Thread(() -> {
//            Integer value = 1;
            final AtomicReference<Integer> value = new AtomicReference<>(1);
            try {
                while (true) {
                    value.set(exchanger.exchange(value.get()));
                    System.out.println("T-A thread worked, the exchange value " + value.get());
                    TimeUnit.SECONDS.sleep(3);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T-A").start();

        new Thread(() -> {
            final AtomicReference<Integer> value = new AtomicReference<>(2);
            try {
                while (true) {
                    value.set(exchanger.exchange(value.get()));
                    System.out.println("T-B thread worked, the exchange value " + value.get());
                    TimeUnit.SECONDS.sleep(2);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T-B").start();
    }
}
