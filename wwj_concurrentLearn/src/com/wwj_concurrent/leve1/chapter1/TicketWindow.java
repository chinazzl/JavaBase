package com.wwj_concurrent.leve1.chapter1;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/5/7 14:39
 * @Modified By：
 *      Thread类不支持线程的共享
 */
public class TicketWindow extends Thread {

    private final int MAX_CUSTOM = 50;
    //使用 static 进行 数据的共享
    private  int custom_index = 1;
    private String customName ;

    private static Object MONITOR = new Object();

    public TicketWindow(String customName) {
        this.customName = customName;
    }

    @Override
    public void run() {

        synchronized (TicketWindow.class) {
            while (custom_index <= MAX_CUSTOM) {
                try {
                    System.out.println("当前消费者 ：" + customName + " ;  排队号码为 ：" + custom_index++);
                    Thread.sleep(10_000_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
