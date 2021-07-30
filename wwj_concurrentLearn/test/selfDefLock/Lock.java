package selfDefLock;

import java.util.Collection;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2021/1/17 12:48
 * @Modified By：
 */
public interface Lock {

    class TimeOutException extends RuntimeException {
        String message;

        public TimeOutException(String message) {
            super(message);
        }
    }

    //上锁
    void lock() throws InterruptedException;

    //制定时间加锁
    void lock(long milles) throws InterruptedException, TimeOutException;

    // 解锁
    void unLock();

    //获取 已上锁的 线程
    Collection<Thread> getBlockedThreads();

    //获取上锁的数量
    int getBlockedSize();
}
