package com.wwj_concurrent.leve2.chapter2.observerdesigner.threadobserver;

import java.util.List;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.leve2.chapter2.observerdesigner.threadobserver
 * @Description:
 * @Date: 2021/6/18 14:22
 */
public class RunnableObserver implements LifeCycleListener {

    private final Object LOCK = new Object();

    public void concurrentQuery(List<String> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }
        ids.forEach(id -> new Thread(new ObserverableRunner(this) {
            @Override
            public void run() {
                try {
                    notifyChange(new RunnableEvent(RunnableState.RUNNABLE, Thread.currentThread(), null));
                    System.out.println("query data id is " + id);
                    Thread.sleep(1000L);
                    notifyChange(new RunnableEvent(RunnableState.DONE, Thread.currentThread(), null));
                } catch (Exception e) {
                    notifyChange(new RunnableEvent(RunnableState.ERROR, Thread.currentThread(), e));
                }
            }
        }).start());
    }

    @Override
    public void onEvent(ObserverableRunner.RunnableEvent runnableEvent) {
        synchronized (LOCK) {
            System.out.println("The Runnable thread is [" + runnableEvent.getThread().getName() + "] has data changed. Thread State is " + runnableEvent.getRunnableState() + " .");
            if (runnableEvent.getThrowable() != null) {
                System.out.println("The Runnable thread is [" + runnableEvent.getThread().getName() + "] process Fail");
                runnableEvent.getThrowable().printStackTrace();
            }
        }
    }
}
