package com.wwj_concurrent.leve2.chapter2.design.activeobjcetpattern;

/**********************************
 * @author zhang zhao lin
 * @date 2021年08月04日 23:38
 * @Description
 **********************************/
public class ScheduleThread extends Thread{

    private final ActivationQueue activityQueues ;

    public ScheduleThread(ActivationQueue activityQueues) {
        this.activityQueues = activityQueues;
    }

    @Override
    public void run() {
        while (true) {
            activityQueues.take().execute();
        }
    }

    public void invoke(MethodRequest request) {
        this.activityQueues.put(request);
    }
}
