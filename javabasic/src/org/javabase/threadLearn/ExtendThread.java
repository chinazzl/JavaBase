package org.javabase.threadLearn;

/**
 * Created by Felix on 2017/7/31.
 */
public class ExtendThread extends Thread {
    private String name;

    public ExtendThread(String name) {
        this.name = name;
    }

    @Override
    public void run(){
        for (int i = 0; i < 5; i++) {
            try {
                sleep(1000);
                System.out.println(name + "运行" + i);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
