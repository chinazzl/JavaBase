package com.wwj_concurrent.leve2.chapter2.design.threadlocal;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhang zhao lin
 * @date 2021年06月28日 23:30
 * @Description 始终以当前线程为key值
 */
public class SimilarThreadLocal<T> {
    private final Map<Thread, T> map = new HashMap<>();

    public synchronized void set(T t) {
        Thread thread = Thread.currentThread();
        map.put(thread, t);
    }

    public synchronized T get() {
        T t = map.get(Thread.currentThread());
        if (t == null) {
            return initValue();
        }
        return t;
    }

    // 允许 子类重写
    public T initValue() {
        return null;
    }

}
