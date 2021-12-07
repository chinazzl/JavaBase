package com.wwj_concurrent.level3.juc.phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @author Julyan
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.level3.juc.phaser
 * @Description: Phaser可以动态的注册和剔除
 *              arriveAndDeregister: 将不需要的parties进行剔除
 *              register：将一个线程注册到Phaser中添加一个parties
 * @Date: 2021/12/3 10:15
 */
public class PhaserSimpleApi {
    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        final Phaser phaser = new Phaser(5);
        for (int i = 1; i < 5; i++) {
            new Athlete(i, phaser).start();
        }
        new HungryAthlete(6, phaser).start();
    }

    private static class HungryAthlete extends Thread {
        private final int no;
        private final Phaser phaser;

        private HungryAthlete(int no, Phaser phaser) {
            this.no = no;
            this.phaser = phaser;
        }

        @Override
        public void run() {
            try {
                sport(phaser, "Thread [ " + no + " ]" + " start running.", "Thread [ " + no + " ]" + " end running.");
                sport(phaser, "Thread [ " + no + " ]" + " start swimming.", "Thread [ " + no + " ]" + " end swimming.");
                System.out.println(no + " I am Hungry. Only to rest ,Good Bye~");
                // 将不需要的parties进行剔除
                phaser.arriveAndDeregister();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Athlete extends Thread {
        private int no;
        private final Phaser phaser;

        private Athlete(int no, Phaser phaser) {
            this.no = no;
            this.phaser = phaser;
        }

        @Override
        public void run() {
            try {
                sport(phaser, "Thread [ " + no + " ]" + " start running.", "Thread [ " + no + " ]" + " end running.");

                sport(phaser, "Thread [ " + no + " ]" + " start swimming.", "Thread [ " + no + " ]" + " end swimming.");

                sport(phaser, "Thread [ " + no + " ]" + " start jumping.", "Thread [ " + no + " ]" + " end jumping.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void sport(Phaser phaser, String s, String s2) throws InterruptedException {
        System.out.println(s);
        TimeUnit.SECONDS.sleep(random.nextInt(5));
        System.out.println(s2);
        phaser.arriveAndAwaitAdvance();
    }
}
