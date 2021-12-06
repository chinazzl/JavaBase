package com.wwj_concurrent.leve2.chapter2.design.futurepattern;

/**
 * @author zhang zhao lin
 * @date 2021年06月23日 22:45
 * @Description:
 */
public interface FutureTask<T> {

    /**
     * 真实 调用的方法
     * @date 2021/6/23 22:50
     * @return T
     */
    T call() throws InterruptedException;
}
