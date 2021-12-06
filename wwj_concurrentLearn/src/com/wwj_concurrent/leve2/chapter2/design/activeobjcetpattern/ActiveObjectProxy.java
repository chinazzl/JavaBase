package com.wwj_concurrent.leve2.chapter2.design.activeobjcetpattern;

/**********************************
 * @author zhang zhao lin
 * @date 2021年08月03日 22:49
 * @Description: 使用静态代理进行 扩充
 **********************************/
public class ActiveObjectProxy implements ActiveObject {
    private final Servant servant;
    private final ScheduleThread scheduleThread;

    public ActiveObjectProxy(Servant servant, ScheduleThread scheduleThread) {
        this.servant = servant;
        this.scheduleThread = scheduleThread;
    }

    @Override
    public Result makeString(int count, char filterChar) {
        FutureResult futureResult = new FutureResult();
        scheduleThread.invoke(new MakeStringRequest(servant,futureResult,count,filterChar));
        return futureResult;
    }

    @Override
    public void displayString(String text) {

    }
}
