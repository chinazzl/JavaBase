package com.wwj_concurrent.level3.juc.phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**********************************
 * @author zhang zhao lin
 * @date 2021年12月09日 22:40
 * @Description awaitAdvice()：如果phase 等于当前的phase则阻塞，如果不等于当前的phase 则不阻塞
 **********************************/
public class PhaserSimpleApi4 {
    public static void main(String[] args) {
        final Phaser phaser = new Phaser(7);
       /* new Thread(() -> {
            phaser.awaitAdvance(0);
            System.out.println("=======");
        }).start();*/
        IntStream.rangeClosed(0, 5).boxed().map(i -> phaser).forEach(WaitAdviceTask::new);
        System.out.println(phaser.getPhase());
        System.out.println("------------------------");
    }

    static class WaitAdviceTask extends Thread {
        private final Phaser phaser;

        WaitAdviceTask(Phaser phaser) {
            this.phaser = phaser;
            start();
        }

        @Override
        public void run() {
            System.out.println(getName() + " is working.");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            phaser.arrive();
            System.out.println(getName() + " finished.");
        }
    }

}
