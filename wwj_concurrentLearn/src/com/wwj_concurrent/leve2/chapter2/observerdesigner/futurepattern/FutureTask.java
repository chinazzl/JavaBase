package com.wwj_concurrent.leve2.chapter2.observerdesigner.futurepattern;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.leve2.chapter2.observerdesigner.futurepattern
 * @Description: 调用具体 业务逻辑
 * @Date: 2021/6/29 10:48
 */
public interface FutureTask<T> {
    T call();
}
