package com.wwj_concurrent.leve2.chapter2.observerdesigner.book.designobserver;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.leve2.chapter2.observerdesigner.book.designobserver
 * @Description: Observerable 提供用户调用接口
 * @Date: 2021/6/22 9:40
 */
public interface IThreadObserverable<T> {

    enum RunnableState {
        STARTED,DONE,ERROR;
    }

    RunnableState getRunnableState();
}
