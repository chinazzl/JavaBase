package com.wwj_concurrent.leve2.chapter2.design.activeobjcetpattern;

/**********************************
 * @author zhang zhao lin
 * @date 2021年08月04日 23:44
 * @Description
 **********************************/
public class RealResult implements Result{

    private final Object resultValue;

    public RealResult(Object resultValue) {
        this.resultValue = resultValue;
    }

    @Override
    public Object getResultValue() {
        return this.resultValue;
    }
}
