package com.wwj_concurrent.leve2.chapter2.observerdesigner.book.designobserver;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.leve2.chapter2.observerdesigner.book.designobserver
 * @Description: 默认 实现
 * @Date: 2021/6/22 9:58
 */
public class EmptyThreadLifeCycle<T> implements ThreadLifeCycle<T> {

    @Override
    public void started(Thread thread) {
        System.out.println(" Default started method install");
    }

    @Override
    public void finished(Thread thread, Object result) {
        System.out.println(" Default finished method install");

    }

    @Override
    public void error(Thread thread, Exception e) {
        System.out.println(" Oh~ Occur Error");
    }
}
