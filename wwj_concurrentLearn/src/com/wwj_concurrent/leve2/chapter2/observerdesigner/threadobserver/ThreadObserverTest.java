package com.wwj_concurrent.leve2.chapter2.observerdesigner.threadobserver;

import java.util.Arrays;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.leve2.chapter2.observerdesigner.threadobserver
 * @Description:
 * @Date: 2021/6/18 15:06
 */
public class ThreadObserverTest {
    public static void main(String[] args) {
        RunnableObserver runnableObserver = new RunnableObserver();
        runnableObserver.concurrentQuery(Arrays.asList("1","2","3"));
    }
}
