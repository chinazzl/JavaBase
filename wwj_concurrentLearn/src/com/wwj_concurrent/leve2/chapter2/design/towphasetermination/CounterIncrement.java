package com.wwj_concurrent.leve2.chapter2.design.towphasetermination;

import java.util.Random;

/**
 * @author zhang zhao lin
 * @date 2021年07月11日 22:17
 * @Description
 */
public class CounterIncrement extends Thread {

    private boolean termination = false;

    private static final Random random = new Random(System.currentTimeMillis());

    private int count = 0;

    @Override
    public void run() {

        try {
            while (!termination) {
                System.out.println("The thread " + Thread.currentThread().getName() + " will be increment " + count++);
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.closed();
        }

    }

    private void closed() {
        System.out.println("do something ... finally");
    }

    public void stoped() {
        this.termination = true;
        this.interrupt();
    }
}
