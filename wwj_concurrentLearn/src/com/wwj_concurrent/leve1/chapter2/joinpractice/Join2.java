package com.wwj_concurrent.leve1.chapter2.joinpractice;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.leve1.chapter2.joinpractice
 * @Description: 如果一个线程A（这里指的是main）执行了<code>Thread thread = new Thread(new DominoRunnable(previous),String.valueOf(i));</code>
 * thread.join()语句，其含义是：当前线程A等待thread线程终止之后才
 * 从thread.join()返回。线程Thread除了提供join()方法之外，还提供了join(long millis)和join(long
 * millis,int nanos)两个具备超时特性的方法。这两个超时方法表示，如果线程thread在给定的超时
 * 时间里没有终止，那么将会从该超时方法中返回
 * @Date: 2021/11/12 9:55
 */
public class Join2 {
    public static void main(String[] args) throws InterruptedException {
        Thread previous = Thread.currentThread();
        System.out.println("previous Thread is " + previous.getName());
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new DominoRunnable(previous), String.valueOf(i));
            thread.start();
            previous = thread;
        }
        Thread.sleep(2_000);
        System.out.println(Thread.currentThread().getName() + " terminate. all");
    }

    static class DominoRunnable implements Runnable {
        private Thread thread;

        public DominoRunnable(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " terminate. ");
        }
    }
}
