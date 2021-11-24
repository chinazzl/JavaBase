package com.wwj_concurrent.level3.lock;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

import static sun.misc.PostVMInitHook.run;

/**
 * @author Julyan
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.level3.lock
 * @Description:
 * @Date: 2021/11/23 16:52
 */
public class ReentLock1 {

    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
       /* IntStream.range(0,100).forEach(i -> new Thread(() -> {
            getLock(i);
        }).start());*/
        Thread t1 = new Thread(ReentLock1::testLockInterruptAbility);
        t1.start();

        TimeUnit.SECONDS.sleep(1);

        Thread t2 = new Thread(ReentLock1::testLockInterruptAbility);
        t2.start();
        // 防止其他线程没有执行完就获取monitor
        TimeUnit.SECONDS.sleep(1);

        System.out.println("=== after worked ===");
        System.out.println(" isLock: => " + lock.isLocked());
        // 查询是否有线程正在等待获取此锁
        System.out.println("===  查询是否有线程正在等待获取此锁 ===");
        System.out.println(" hasQueuedThreads => " + lock.hasQueuedThreads());
        // 查询给定线程是否正在等待获取此锁
        System.out.println("=== 查询给定线程是否正在等待获取此锁 === ");
        System.out.println(" hasQueuedThreads(Thread) => " + lock.hasQueuedThread(t2));
        System.out.println(" hasQueuedThreads(Thread) => " + lock.hasQueuedThread(t1));
        // 返回等待获取此锁的线程数的估计值
        System.out.println("=== 返回等待获取此锁的线程数的估计值 === ");
        System.out.println(" getQueueLength() => " + lock.getQueueLength());

//        t2.interrupt();
//        System.out.println("===");

    }

    /**
     *  尝试进行获取lock，它不会被打断
     */
    private static void testTryLock() {
        if (lock.tryLock()) {
            try {
                Optional.of("Thread Name => " + Thread.currentThread().getName() + " will work.").ifPresent(System.out::println);
                while (true) {

                }
            }finally {
                lock.unlock();
            }
        }else {
            Optional.of("oh... Thread Name => " + Thread.currentThread().getName() + " didn't get lock.").ifPresent(System.out::println);

        }
    }

    /**
     * 测试是否可以打断lock
     */
    private static void testLockInterruptAbility() {
        try {
            // lock 不允许被中断
//            lock.lock();
            lock.lockInterruptibly();
            // 查询当前线程持有该锁的次数。
            System.out.println(" 查询当前线程持有该锁的次数。");
            System.out.println(Thread.currentThread().getName() + " : " + lock.getHoldCount());
            Optional.of("Thread Name => " + Thread.currentThread().getName() + " will work.").ifPresent(System.out::println);
            while (true) {

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void getLock() {
        try {
            // lock 不允许被中断
            lock.lock();
            Optional.of("Thread Name => " + Thread.currentThread().getName() + " will work.").ifPresent(System.out::println);
        } finally {
            lock.unlock();
        }
    }
}
