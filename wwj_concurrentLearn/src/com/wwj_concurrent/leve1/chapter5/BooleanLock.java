package com.wwj_concurrent.leve1.chapter5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeoutException;

import static java.lang.System.currentTimeMillis;
import static java.lang.Thread.currentThread;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/9/16 15:15
 * @Modified By：
 */
public class BooleanLock implements Lock {

    // 表示当前拥有锁的线程
    private Thread currentThread;

    // 锁开关，false代表当前该锁没有任何线程获得或者已经释放，true 代表该锁已经被某个线程获得，也就是currentThread
    private boolean locked = false;

    // 存储哪些线程在获取当前线程时进入了阻塞状态
    private final List<Thread> blockList = new ArrayList<>();

    @Override
    public void lock() throws InterruptedException {
        synchronized (this) {
            while (locked) {
                //如果当前锁已经被某个线程获得，则该线程加入阻塞队列，并且把当前线程wait释放对monitor的所有权
                blockList.add(currentThread());
                this.wait();
            }
            blockList.remove(currentThread());
            this.locked = true;
            this.currentThread = currentThread();
        }
    }

    @Override
    public void lock(long milles) throws InterruptedException, TimeoutException {
        synchronized (this) {
            if (milles < 0) {
                this.lock();
            } else {
                long remainingMills = milles;
                long endMills = currentTimeMillis() + remainingMills;
                while (locked) {
                    // 当前线程被其他线程唤醒或者在指定的wait时间到了以后还没有获得锁
                    if (remainingMills <= 0) {
                        throw new TimeoutException("Can not get the lock during " + milles);
                    }
                    if (!blockList.contains(currentThread())) {
                        blockList.add(currentThread());
                        this.wait(remainingMills);
                        remainingMills = endMills - currentTimeMillis();
                    }
                    blockList.remove(currentThread());
                    this.locked = true;
                    this.currentThread = currentThread();
                }
            }
        }

    }

    /**
     * 解锁
     */
    @Override
    public void unlock() {
        synchronized (this) {
            // 判断当前线程是否为获取锁的那个线程，只有加了锁的线程才有资格进行解锁
            if (currentThread == currentThread()) {
                //将锁的locked状态修改为false
                this.locked = false;
                //通知其他在wait set中的线程，使用notify 或 notifyAll都行
                this.notify();
            }
        }

    }

    @Override
    public List<Thread> getBlockThreads() {
        return Collections.unmodifiableList(blockList);
    }
}
