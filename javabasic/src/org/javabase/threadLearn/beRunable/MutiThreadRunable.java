package org.javabase.threadLearn.beRunable;

/**
 * Created by Felix on 2017/7/31.
 */
public class MutiThreadRunable implements Runnable {
    private String name;

    public MutiThreadRunable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                new Thread().sleep(1000);
                System.out.println(name + "运行" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
