package com.wwj_concurrent.leve2.chapter2.observerdesigner.book.designobserver;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.leve2.chapter2.observerdesigner.book.designobserver
 * @Description:
 * @Date: 2021/6/22 9:49
 */
public interface ThreadLifeCycle<T> {

    void started(Thread thread);

    void finished(Thread thread, T result);

    void error(Thread thread, Exception e);
}
