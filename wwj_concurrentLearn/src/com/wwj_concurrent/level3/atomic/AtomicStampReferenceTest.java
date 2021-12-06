package com.wwj_concurrent.level3.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**********************************
 * @author zhang zhao lin
 * @date 2021年10月08日 22:28
 * @Description 使用AtomicStampReference 解决ABA问题
 *              ABA问题：
 *                  Thread-1 引用类型 A，Thread-2 引用类型 A ，在线程Thread-1 还没有进行更改的时候
 *                  Thead-2 进行修改A 变为 引用B 紧接着又变为A，之后 Thread-1 进行修改A 造成问题
 **********************************/
public class AtomicStampReferenceTest {
    public static void main(String[] args) throws InterruptedException {
        final AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 0);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    TimeUnit.SECONDS.sleep(1);
                    // 参数：1. 期望的数据 2. 修改后的数据 3. 当前的token 4. 新的token
                    boolean success = atomicStampedReference.compareAndSet(100, 101,
                            atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
                    System.out.println(success);
                    success = atomicStampedReference.compareAndSet(101, 100,
                            atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
                    System.out.println(success);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int stamp = atomicStampedReference.getStamp();
                    System.out.println(stamp);
                    TimeUnit.SECONDS.sleep(2);
                    boolean b = atomicStampedReference.compareAndSet(100, 101, stamp, stamp + 1);
                    System.out.println(b);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
