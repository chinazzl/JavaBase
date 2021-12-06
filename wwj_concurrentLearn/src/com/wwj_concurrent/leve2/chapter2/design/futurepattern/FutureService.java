package com.wwj_concurrent.leve2.chapter2.design.futurepattern;

/**
 * @author zhang zhao lin
 * @date 2021年06月23日 23:05
 * @Description
 */
public class FutureService<T> {

    public Future<T> submit(FutureTask<T> task) {
        AsyncFuture<T> asyncFuture = new AsyncFuture<T>();
        new Thread(() -> {
            T result = null;
            try {
                result = task.call();
                asyncFuture.done(result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();
        return asyncFuture;
    }
}
