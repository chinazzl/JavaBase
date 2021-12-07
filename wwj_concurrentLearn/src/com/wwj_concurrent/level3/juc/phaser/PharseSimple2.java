package com.wwj_concurrent.level3.juc.phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @author Julyan
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.level3.juc.phaser
 * @Description: 指定指定parties的Pharse
 * @Date: 2021/12/2 15:58
 */
public class PharseSimple2 {

    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        final Phaser phaser = new Phaser(5);
        for (int i = 1; i < 6; i++) {
            new Task(i, phaser).start();
        }

        phaser.register();
        phaser.arriveAndAwaitAdvance();
        System.out.println("All Sport has done.");
    }

    private static class Task extends Thread {
        private int no;
        private final Phaser phaser;

        private Task(int no, Phaser phaser) {
            this.no = no;
            this.phaser = phaser;
        }

        @Override
        public void run() {
            try {
                System.out.println("Thread [ " + getName() + " ]-" + no + " start running.");
                TimeUnit.SECONDS.sleep(random.nextInt(5));
                System.out.println("Thread [ " + getName() + " ]-" + no + " end running.");
                phaser.arriveAndAwaitAdvance();

                System.out.println("Thread [ " + getName() + " ]-" + no + " start swimming.");
                TimeUnit.SECONDS.sleep(random.nextInt(5));
                System.out.println("Thread [ " + getName() + " ]-" + no + " end swimming.");
                phaser.arriveAndAwaitAdvance();

                System.out.println("Thread [ " + getName() + " ]-" + no + " start jumping.");
                TimeUnit.SECONDS.sleep(random.nextInt(5));
                System.out.println("Thread [ " + getName() + " ]-" + no + " end jumping.");
                phaser.arriveAndAwaitAdvance();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
