package com.wwj_concurrent.leve2.chapter2.observerdesigner.futurepattern;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.leve2.chapter2.observerdesigner.futurepattern
 * @Description:
 * @Date: 2021/6/29 11:00
 */
public class FutureService<T> {

    public Future<T> submit(final FutureTask<T> futureTask) {
        AsyncFutureTask<T> asyncFutureTask = new AsyncFutureTask<>();
        new Thread(() -> {
            T call = futureTask.call();
            asyncFutureTask.done(call);
        }).start();

        return asyncFutureTask;
    }
}
