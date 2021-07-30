package selfDefLock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2021/1/17 12:53
 * @Modified By：
 */
public class BooleanLock implements Lock {
    //初始化 initValue， 若为true ，代表 已经获得锁，否则未获取锁，
    private Boolean initValue;

    private Collection<Thread> blockedThreads = new ArrayList<>();

    private Thread currentThread;

    public BooleanLock() {
        this.initValue = false;
    }

    @Override
    public void lock() throws InterruptedException {
        synchronized (this) {
            while (initValue) {
                blockedThreads.add(Thread.currentThread());
                this.wait();
            }
            //获取锁将阻塞的锁移除
            blockedThreads.remove(Thread.currentThread());
            initValue = true;
            this.currentThread = Thread.currentThread();
        }

    }

    @Override
    public synchronized void lock(long milles) throws InterruptedException, TimeOutException {
        //如果 指定时间 小于等于0 则默认执行无时长的加锁方法
        if (milles <= 0) {
            lock();
        }
        long hasReminding = milles;
        long endTimeMill = System.currentTimeMillis() + milles;
        while (initValue) {
            if (hasReminding <= 0) {
                throw new TimeOutException("Time out");
            }
            this.blockedThreads.add(Thread.currentThread());
            this.wait(milles);
            hasReminding = endTimeMill - System.currentTimeMillis();
        }
        blockedThreads.remove(Thread.currentThread());
        this.initValue = true;
    }

    @Override
    public void unLock() {
        synchronized (this) {
            if (this.currentThread == Thread.currentThread()) {
                this.initValue = false;
                System.out.println(Thread.currentThread().getName() + " has been release lock");
                this.notifyAll();
            }
        }

    }

    @Override
    public Collection<Thread> getBlockedThreads() {
        return Collections.unmodifiableCollection(blockedThreads);
    }

    @Override
    public int getBlockedSize() {
        return blockedThreads.size();
    }
}
