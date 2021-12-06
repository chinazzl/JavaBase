package com.wwj_concurrent.leve2.chapter2.design.activeobjcetpattern;

/**********************************
 * @author zhang zhao lin
 * @date 2021年08月09日 22:15
 * @Description
 **********************************/
public class ActiveObjectFactory {

    public static ActiveObject createActiveObject() {
        Servant servant = new Servant();
        ActivationQueue activationQueue = new ActivationQueue();
        ScheduleThread scheduleThread = new ScheduleThread(activationQueue);
        ActiveObjectProxy proxy = new ActiveObjectProxy(servant,scheduleThread);
        scheduleThread.start();
        return proxy;
    }
}
