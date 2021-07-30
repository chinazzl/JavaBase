package com.wwj_concurrent.leve2.chapter2.observerdesigner.threadobserver;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.leve2.chapter2.observerdesigner.threadobserver
 * @Description:
 * @Date: 2021/6/18 14:20
 */
public interface LifeCycleListener {

    void onEvent(ObserverableRunner.RunnableEvent runnableEvent);
}
