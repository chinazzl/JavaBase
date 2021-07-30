package com.wwj_concurrent.leve2.chapter2.observerdesigner.book.designobserver;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.leve2.chapter2.observerdesigner.book.designobserver
 * @Description:
 * @Date: 2021/6/22 9:51
 */
public class ThreadObserverable<T> extends Thread implements IThreadObserverable<T> {


    private ThreadLifeCycle<T> threadLifeCycle;

    private RunnableState runnableState;

    private Task<T> task;

    public ThreadObserverable(Task<T> task) {
        this(new EmptyThreadLifeCycle<T>(), task);
    }

    public ThreadObserverable(ThreadLifeCycle<T> threadLifeCycle, Task<T> task) {
        this.threadLifeCycle = threadLifeCycle;
        this.task = task;
    }

    @Override
    public final void run() {
        try {
            this.update(RunnableState.STARTED, threadLifeCycle, null, null);
            T result = task.call();
            this.update(RunnableState.DONE, threadLifeCycle, result, null);
        } catch (Exception e) {
            e.printStackTrace();
            this.update(RunnableState.ERROR, threadLifeCycle, null, e);
        }

    }

    @Override
    public RunnableState getRunnableState() {
        return this.runnableState;
    }

    private void update(RunnableState runnableState, ThreadLifeCycle<T> threadLifeCycle, T result, Exception e) {
        try {
            if (runnableState == null) {
                return;
            }
            this.runnableState = runnableState;

            switch (runnableState) {
                case STARTED:
                    threadLifeCycle.started(Thread.currentThread());
                    break;
                case DONE:
                    threadLifeCycle.finished(Thread.currentThread(), result);
                    break;
                case ERROR:
                    threadLifeCycle.error(Thread.currentThread(), e);
                    break;
            }
        } catch (Exception cause) {
            if (runnableState == RunnableState.ERROR) {
                throw cause;
            }
        }

    }
}
