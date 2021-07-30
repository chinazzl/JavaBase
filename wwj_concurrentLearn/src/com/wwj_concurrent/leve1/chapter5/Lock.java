package com.wwj_concurrent.leve1.chapter5;

import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/9/16 15:07
 * @Modified By：
 *      自定义BiileanLock接口
 */
public interface Lock {

    // lock() 永远阻塞，除非获取到了锁，
    void lock() throws InterruptedException;

    // 可以被中断，增加了超时功能
    void lock(long milles) throws InterruptedException, TimeoutException;

    //进行锁的释放
    void unlock();

    //获取当前哪些线程被阻塞
    List<Thread> getBlockThreads();
}
