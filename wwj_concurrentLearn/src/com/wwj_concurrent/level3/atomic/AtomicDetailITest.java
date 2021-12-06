package com.wwj_concurrent.level3.atomic;

/**********************************
 * @author zhang zhao lin
 * @date 2021年09月26日 22:46
 * @Description
 **********************************/
public class AtomicDetailITest {
    //使用 CAS 创建的显示锁
    private final static AtomicTryLock lock = new AtomicTryLock();

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    doSomething2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private static void doSomethings() throws InterruptedException {
        synchronized (AtomicDetailITest.class) {
            System.out.println(Thread.currentThread().getName() + " will get lock");
            Thread.sleep(10_000000);
        }
    }

    private static void doSomething2() throws InterruptedException {
        try {
            lock.tryLock();
            System.out.println(Thread.currentThread().getName() + " will get lock");
            Thread.sleep(10_00000000);
        } finally {
            lock.unlock();
        }
    }
}
