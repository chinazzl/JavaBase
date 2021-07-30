package com.wwj_concurrent.leve2.chapter2.observerdesigner.book.designobserver;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.leve2.chapter2.observerdesigner.threadobserver
 * @Description:
 * @Date: 2021/6/22 10:14
 */
@FunctionalInterface
public interface Task<T> {
    T call();
}
