package com.wwj_concurrent.leve2.chapter2.design.activeobjcetpattern;

/**********************************
 * @author zhang zhao lin
 * @date 2021年08月04日 22:47
 * @Description
 **********************************/
public class FutureResult implements Result {

    private Result result;
    private volatile boolean ready = false;

    public synchronized void setResult(Result resultValue) {
        this.result = resultValue;
        this.ready = true;
        this.notifyAll();
    }

    @Override
    public synchronized Object getResultValue() {
        while (!ready) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return this.result.getResultValue();
    }
}
