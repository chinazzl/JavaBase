package org.javabase.threadLearn.threader;

/**
 * wait方法
 */
public class WaitTest implements Runnable{

    private String name;
    private String pevS;
    private String selfS;

    public WaitTest(String name, String pevS, String selfS) {
        this.name = name;
        this.pevS = pevS;
        this.selfS = selfS;
    }


    /**
     * 程序运行的主要过程就是A线程最先运行，持有C,A对象锁，后释放A,C锁，唤醒B。
     * 线程B等待A锁，再申请B锁，后打印B，再释放B，A锁，唤醒C，
     * 线程C等待B锁，再申请C锁，后打印C，再释放C,B锁，唤醒A。
     */
    @Override
    public void run() {
        int count = 10;
        while (count >0){
            synchronized (pevS){ //先将前一个线程进行加锁
                synchronized (selfS){   //然后再给自身加
                    System.out.print(name);
                    count--;
                    selfS.notify();
                }
                try {
                    /*
                     * wait就是说线程在获取对象锁后，主动释放对象锁同时本线程休眠。
                     * 直到有其它线程调用对象的notify()唤醒该线程，才能继续获取对象锁，并继续执行。
                     */
                    pevS.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}

class demo{
    public static void main(String[] args) throws InterruptedException {
        new Thread(new WaitTest("A","c","a")).start();
        Thread.sleep(100);  //确保按顺序A、B、C执行
        new Thread(new WaitTest("B","a","b")).start();
        Thread.sleep(100);  //确保按顺序A、B、C执行
        new Thread(new WaitTest("C","b","c")).start();

    }
}