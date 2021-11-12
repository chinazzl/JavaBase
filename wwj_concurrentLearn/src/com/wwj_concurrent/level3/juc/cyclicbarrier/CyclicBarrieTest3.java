package com.wwj_concurrent.level3.juc.cyclicbarrier;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.level3.juc.cyclicbarrier
 * @Description:
 * @Date: 2021/11/12 16:50
 */
public class CyclicBarrieTest3 {

    /**
     * 使用 CountDownLatch 模拟 CyclicBarrier
     */
    static class SimilarCyclicBarrier extends CountDownLatch {
        private final Runnable runnable;

        public SimilarCyclicBarrier(int count, Runnable runnable) {
            super(count);
            this.runnable = runnable;
        }

        public void countDown() {
            super.countDown();
            //当count ==0 相当于 所有线程已经执行到起跑线上。可以执行后续代码。
            if (getCount() == 0) {
                runnable.run();
            }
        }
    }


    public static void main(String[] args) {
        SimilarCyclicBarrier similarCyclicBarrier = new SimilarCyclicBarrier(2,() ->{
            System.out.println(" all worker has done. ");
        });
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                similarCyclicBarrier.countDown();
                System.out.println(" T1 access finished ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                similarCyclicBarrier.countDown();
                System.out.println(" T2 access finished ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
