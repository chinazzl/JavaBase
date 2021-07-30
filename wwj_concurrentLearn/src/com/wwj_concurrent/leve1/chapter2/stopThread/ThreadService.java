package com.wwj_concurrent.leve1.chapter2.stopThread;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/12/21 16:27
 * @Modified By：
 * 暴力 终止线程
 */
public class ThreadService {

    private Thread execute;

    private Boolean finished = false;

    public void execute(Runnable task) {
        execute = new Thread() {
            @Override
            public void run() {
                Thread runner = new Thread(task);
                runner.setDaemon(true);
                runner.start();
                try {
                    runner.join();
                    finished = true;
                } catch (InterruptedException e) {
                    System.out.println(this.getName() + "线程被 终止");
                }
            }
        };
        execute.start();
    }

    public void shutDown(long milles) {
        long  current = System.currentTimeMillis();
        while (!finished) {
            if((System.currentTimeMillis() - current)> milles )  {
                execute.interrupt();
                System.out.println("线程超时，执行线程被终止");
                break;
            }
        }
        try {
            execute.sleep(1);
        } catch (InterruptedException e) {
        }
        finished = false;
    }

}
