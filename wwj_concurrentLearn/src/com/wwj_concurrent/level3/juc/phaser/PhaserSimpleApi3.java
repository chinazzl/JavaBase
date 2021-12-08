package com.wwj_concurrent.level3.juc.phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @author Julyan
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.level3.juc.phaser
 * @Description: arrive：不阻塞
 * @Date: 2021/12/8 10:37
 */
public class PhaserSimpleApi3 {

    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
       /* final Phaser phaser = new Phaser(1);
        new Thread(phaser::arrive).start();
        System.out.println(phaser.getPhase());*/
        final Phaser phaser = new Phaser(5){
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                return false;
            }
        };
        for (int i = 0; i < 5; i++) {
            new ArrivePhaser(phaser, i).start();
        }
        phaser.arriveAndAwaitAdvance();
        System.out.println(" all work has been finished.");
    }

    static class ArrivePhaser extends Thread {
        private final Phaser phaser;

        ArrivePhaser(Phaser phaser, int no) {
            super(String.valueOf(no));
            this.phaser = phaser;
        }

        @Override
        public void run() {
            try {
                System.out.println(getName() + " will be started. phaser " + phaser.getPhase());
                PhaserSimpleApi3.sleep();
                System.out.println(getName() + " will be ended. phaser " + phaser.getPhase());
                phaser.arrive();
                PhaserSimpleApi3.sleep();
                System.out.println(getName() + " ===== do other things =====");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void sleep() throws InterruptedException {
        TimeUnit.SECONDS.sleep(random.nextInt(5));
    }


}
