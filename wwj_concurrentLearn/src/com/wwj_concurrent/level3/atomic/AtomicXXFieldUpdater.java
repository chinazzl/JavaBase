package com.wwj_concurrent.level3.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.level3.atomic
 * @Description: XXFieldUpdater
 *
 * 一个基于反射的实用程序，可以对指定类的指定volatile int字段进行原子更新。 此类设计用于原子数据结构，其中同一节点的多个字段独立地进行原子更新。
 * 请注意，此类中compareAndSet方法的保证比其他原子类中的要弱。 因为这个类不能确保该字段的所有使用都适合原子访问的目的，
 * 所以它只能保证与在同一更新程序上的compareAndSet和set其他调用有关的原子性
 *
 * @Date: 2021/10/18 16:46
 */
public class AtomicXXFieldUpdater {

    public static void main(String[] args) {
        AtomicIntegerFieldUpdater<TestMe> atomicIntegerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(TestMe.class, "val");
        TestMe testMe = new TestMe();
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                final int MAX_VALUE = 20;
                for (int j = 0; j < MAX_VALUE; j++) {
//                    boolean b = atomicIntegerFieldUpdater.compareAndSet(testMe, j, 2);
                    int increment = atomicIntegerFieldUpdater.getAndIncrement(testMe);
                    System.out.println("Thread -> " + Thread.currentThread().getName() + " ==> " +increment);
                }
            }).start();
        }
    }

    static class TestMe {
        volatile int val;
    }
}
