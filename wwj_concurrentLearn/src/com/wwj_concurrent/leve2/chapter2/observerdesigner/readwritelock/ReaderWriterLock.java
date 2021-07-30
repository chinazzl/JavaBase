package com.wwj_concurrent.leve2.chapter2.observerdesigner.readwritelock;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.leve2.chapter2.observerdesigner.readwritelock
 * @Description:
 * @Date: 2021/6/22 15:37
 */
public class ReaderWriterLock {

    private int readReaderCount = 0;
    private int readWaiterCount = 0;
    private int writerWriterCount = 0;
    private int writerWaitCount = 0;

    public synchronized void readLock() {
        readWaiterCount++;
        try {
            while (writerWriterCount > 0) {
                this.wait();
            }
            readReaderCount++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWaiterCount--;
        }
    }

    public synchronized void readUnlock() {
        this.notifyAll();
        readReaderCount--;
    }

    public synchronized void writeLock() {
        writerWaitCount++;
        try {
            while (readWaiterCount > 0 || writerWriterCount > 0) {
                this.wait();
            }
            writerWriterCount++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writerWaitCount--;
        }
    }

    public synchronized void writeUnlock() {
        this.notifyAll();
        writerWriterCount--;
    }
}
