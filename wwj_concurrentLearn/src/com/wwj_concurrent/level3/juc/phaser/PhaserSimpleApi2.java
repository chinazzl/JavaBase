package com.wwj_concurrent.level3.juc.phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @author Julyan
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.level3.juc.phaser=
 * @Description: arriveAndAwaitAdvance()：相当于CountDownLatch的await
 *               getPhase()：获取phase，返回当前的阶段号，每调用arriveAndAwaitAdvance()一次，它+1
 *               getRegisteredParties()：获取已经注册的Phaser的parties
 *               getArrivedParties()/getUnarrivedParties()：返回已到达/未到达此移相器当前阶段的已注册方的数量
 * @Date: 2021/12/3 15:08
 */
public class PhaserSimpleApi2 {

    public static void main(String[] args) throws InterruptedException {
        final Phaser phaser = new Phaser(2) {
            /*
                如果返回值为true，则phaser不循环服用，相当于Terminate
             */
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                return false;
            }
        };

        System.out.println(phaser.getPhase());
        phaser.arriveAndAwaitAdvance();
        System.out.println(phaser.getPhase());
        phaser.arriveAndAwaitAdvance();
        System.out.println(phaser.getPhase());

        /*phaser.arriveAndAwaitAdvance();
        phaser.register();
        System.out.println(phaser.getRegisteredParties());*/

       /* phaser.bulkRegister(10);
        System.out.println(phaser.getRegisteredParties());
        System.out.println(phaser.getArrivedParties());
        System.out.println(phaser.getUnarrivedParties());
        new Thread(phaser::arriveAndAwaitAdvance).start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("==================================");
        System.out.println(phaser.getRegisteredParties());
        System.out.println(phaser.getArrivedParties());
        System.out.println(phaser.getUnarrivedParties());*/

        System.out.println("===============OnAdvice=================");
        new OnAdvanceTask("A", phaser).start();
        new OnAdvanceTask("B", phaser).start();
        TimeUnit.SECONDS.sleep(2);

        System.out.println(phaser.getArrivedParties());
        System.out.println(phaser.getUnarrivedParties());

    }

    static class OnAdvanceTask extends Thread {
        private final Phaser phaser;

        OnAdvanceTask(String name, Phaser phaser) {
            super(name);
            this.phaser = phaser;
        }

        @Override
        public void run() {
            System.out.println(getName() + " I am started. The phaser is " + phaser.getPhase());
            phaser.arriveAndAwaitAdvance();
            System.out.println(getName() + " I am ended. ");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (getName().equals("A")) {
                System.out.println(getName() + " I am started. The phaser is " + phaser.getPhase());
                phaser.arriveAndAwaitAdvance();
                System.out.println(getName() + " I am ended. ");
            }
            System.out.println(getName() + "==> " +phaser.isTerminated());
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
//            phaser.arriveAndAwaitAdvance();
        }
    }


}
