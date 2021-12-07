package com.wwj_concurrent.level3.juc.phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author Julyan
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.level3.juc.phaser
 * @Description: 动态添加 参与者
 * @Date: 2021/12/2 14:38
 */
public class PhaserSimpleExample {
    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        final Phaser phaser = new Phaser();
        IntStream.rangeClosed(1, 5).boxed().map(i -> phaser).forEach(Task::new);

        // 将main线程 注册到phaser Adds a new unarrived party to this phaser.
        phaser.register();
        phaser.arriveAndAwaitAdvance();
        System.out.println("all Thread has done.");
    }

    private static class Task extends Thread {
        private final Phaser phaser;

        private Task(Phaser phaser) {
            this.phaser = phaser;
            this.phaser.register();
            start();
        }

        @Override
        public void run() {
            try {
                System.out.println("Thread [ " + getName() + " ] is working.");
                TimeUnit.SECONDS.sleep(random.nextInt(5));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 相当于CountDownLatch 的 await
            phaser.arriveAndAwaitAdvance();
        }
    }
}
