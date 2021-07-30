package com.wwj_concurrent.leve2.chapter2.observerdesigner.threadobserver;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.leve2.chapter2.observerdesigner.threadobserver
 * @Description:
 * @Date: 2021/6/18 14:02
 */
public abstract class ObserverableRunner implements Runnable{

    protected LifeCycleListener lifeCycleListener;

    public ObserverableRunner(LifeCycleListener lifeCycleListener) {
        this.lifeCycleListener = lifeCycleListener;
    }

    protected void notifyChange(final RunnableEvent runnableEvent) {
        lifeCycleListener.onEvent(runnableEvent);
    }

    public enum RunnableState {
        RUNNABLE,DONE,ERROR;
    }

    public class RunnableEvent{
        private RunnableState runnableState;
        private Thread thread;
        private Throwable throwable;

        public RunnableEvent(RunnableState runnableState, Thread thread, Throwable throwable) {
            this.runnableState = runnableState;
            this.thread = thread;
            this.throwable = throwable;
        }

        public RunnableState getRunnableState() {
            return runnableState;
        }

        public void setRunnableState(RunnableState runnableState) {
            this.runnableState = runnableState;
        }

        public Thread getThread() {
            return thread;
        }

        public void setThread(Thread thread) {
            this.thread = thread;
        }

        public Throwable getThrowable() {
            return throwable;
        }

        public void setThrowable(Throwable throwable) {
            this.throwable = throwable;
        }
    }
}
