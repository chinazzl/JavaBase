package com.wwj_concurrent.leve2.chapter2.design.towphasetermination;

/**
 * @author zhang zhao lin
 * @date 2021年07月11日 22:21
 * @Description
 */
public class CounterIncrementTest {
    public static void main(String[] args) throws InterruptedException {
        final CounterIncrement counterIncrement = new CounterIncrement();
        counterIncrement.start();

        Thread.sleep(10_000);
        counterIncrement.stoped();
    }
}
