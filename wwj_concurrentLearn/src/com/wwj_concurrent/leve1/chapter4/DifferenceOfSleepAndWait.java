package com.wwj_concurrent.leve1.chapter4;

import java.util.stream.Stream;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2021/1/4 10:49
 * @Modified By：
 * Sleep 和 wait的区别
 * 1. sleep 是 Thread类的静态方法，wait是 Object的方法
 * 2. sleep 是不释放锁，wait 调用后释放该cpu锁的所有权
 * 3. sleep 一段时间后自动变为可运行状态，wait需要调用notify 或者 notifyAll方法
 * 4. sleep 不需要依赖 Object对象，wait 需要依赖Object对象
 */
public class DifferenceOfSleepAndWait {

    private static final Object LOCK = new Object();

    public static void main(String[] args) {

        Stream.of("t1", "t2").forEach(name ->
                new Thread(name) {
                    @Override
                    public void run() {
                        m1();
                    }

                }.start()
        );
    }

    public static void m1() {
        synchronized (LOCK) {
            System.out.println("The method m1 add " + Thread.currentThread().getName() + " entered");
            try {
                Thread.sleep(50_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void m2() {
        synchronized (LOCK) {
            System.out.println("The method m2 add " + Thread.currentThread().getName() + " entered");
            try {
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
