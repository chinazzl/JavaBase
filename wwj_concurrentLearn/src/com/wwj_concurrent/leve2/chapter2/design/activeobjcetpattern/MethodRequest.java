package com.wwj_concurrent.leve2.chapter2.design.activeobjcetpattern;

/**********************************
 * @author zhang zhao lin
 * @date 2021年07月24日 14:20
 * @Description 包含method 每一个方法
 **********************************/
public abstract class MethodRequest {

    protected final Servant servant;

    protected final FutureResult futureResult;

    public MethodRequest(Servant servant, FutureResult futureResult) {
        this.servant = servant;
        this.futureResult = futureResult;
    }

    public abstract void execute();
}
