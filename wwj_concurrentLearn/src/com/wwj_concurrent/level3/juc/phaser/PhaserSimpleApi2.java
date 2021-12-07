package com.wwj_concurrent.level3.juc.phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @author Julyan
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.level3.juc.phaser
 * @Description:
 * @Date: 2021/12/3 15:08
 */
public class PhaserSimpleApi2 {

    public static void main(String[] args) {
        final Phaser phaser = new Phaser(5);

        for (int i = 1; i < 6; i++) {
            new Task(phaser).start();
        }
        phaser.arriveAndAwaitAdvance();
        System.out.println("=========================");
        System.out.println(phaser.getUnarrivedParties());
        System.out.println(phaser.getArrivedParties());
    }

    static class OnAdvanceTask extends Thread {
        private final Phaser phaser;

        OnAdvanceTask(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
        }
    }

    static class Task extends Thread {
        private final Phaser phaser;

        Task(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            try {
                System.out.println("Thread [" + getName() + "] start working...");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("Thread [" + getName() + "] end working...");
                phaser.arriveAndAwaitAdvance();

                System.out.println("Thread [" + getName() + "] start resting...");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("Thread [" + getName() + "] end resting...");
                phaser.arriveAndAwaitAdvance();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            phaser.arriveAndAwaitAdvance();
        }
    }


}
