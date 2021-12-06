package com.wwj_concurrent.level3.atomic;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**********************************
 * @author zhang zhao lin
 * @date 2021年09月26日 20:33
 * @Description
 **********************************/
public class VolitatileIntTest {
//    private volatile static Integer counter = 0;

    private static final Set<Integer> set = Collections.synchronizedSet(new HashSet<>());

    private static final AtomicInteger atomicCounter = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            int val = 0;
            while (val < 500) {
                int counter = atomicCounter.getAndIncrement();
                set.add(counter);
                System.out.println("The Thread1 is " + Thread.currentThread().getName() + "; The Number is " + counter);
                val++;

            }
        });

        Thread t2 = new Thread(() -> {
            int val = 0;
            while (val < 500) {
                int counter = atomicCounter.getAndIncrement();
                set.add(counter);
                System.out.println("The Thread2 is " + Thread.currentThread().getName() + "; The Number is " + counter);
                val++;
            }
        });

        Thread t3 = new Thread(() -> {
            int val = 0;
            while (val < 500) {
                int counter = atomicCounter.getAndIncrement();
                set.add(counter);
                System.out.println("The Thread is " + Thread.currentThread().getName() + "; The Number is " + counter);
                val++;
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();

        System.out.println(set.size());
    }
}