package com.wwj_concurrent.level3.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**********************************
 * @author zhang zhao lin
 * @date 2021年10月18日 21:49
 * @Description 对于没有修饰Atom类型的类属性，具有原子性操作
 * 1. 必须是volatile 修饰
 * 2. 非private（如果是当前类 也可以是private 或 protected）
 * 3. 类型要一致
 **********************************/
public class AtomicXXXFiledUpdater {

    public static void main(String[] args) {
        AtomicIntegerFieldUpdater<TestMe> atomicIntegerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(TestMe.class, "v");

        TestMe testMe = new TestMe();

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                final int MAX = 20;
                for (int j = 0; j < MAX; j++) {
                    int increment = atomicIntegerFieldUpdater.getAndIncrement(testMe);
                    System.out.println(Thread.currentThread().getName() + " ==> " + increment);
                }
            }).start();
        }
    }

    static class TestMe {
        volatile int v;
    }
}
