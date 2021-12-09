package com.wwj_concurrent.level3.juc.phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**********************************
 * @author zhang zhao lin
 * @date 2021年12月09日 23:15
 * @Description interupt Phaser
 **********************************/
public class PhaserSimpleApi5 {
    public static void main(String[] args) {
        final Phaser p = new Phaser(5);
        // 无法进行打断
        /*Thread thread = new Thread(() -> p.arriveAndAwaitAdvance());
        thread.interrupt();*/
        Thread thread = new Thread(() -> {
            try {
                /*
                    当phase 等于 当前的位移前进值 则阻塞住，如果不等于则向下执行
                 */
                p.awaitAdvanceInterruptibly(1);
                System.out.println("do something...");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("end.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();

        System.out.println("---");
    }
}
