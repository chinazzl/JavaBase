package com.wwj_concurrent.level3.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**********************************
 * @author zhang zhao lin
 * @date 2021年09月26日 22:58
 * @Description 使用CAS 显示锁
 **********************************/
public class AtomicTryLock {

    private final AtomicInteger lock = new AtomicInteger(0);

    // 定义一个线程 用来排斥 其他线程
    private Thread lockThread;

    public void tryLock() throws GetLockFailException {
        boolean success = lock.compareAndSet(0, 1);
        if (!success)
            throw new GetLockFailException("Get Lock Fail, The expect will rollback");
        else
            lockThread = Thread.currentThread();
    }

    public void unlock() {
        if (0 == lock.get()) {
            return;
        }
        if (lockThread == Thread.currentThread())
            lock.compareAndSet(1, 0);
    }
}
