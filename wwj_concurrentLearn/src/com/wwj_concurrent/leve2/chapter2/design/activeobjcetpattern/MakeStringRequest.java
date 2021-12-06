package com.wwj_concurrent.leve2.chapter2.design.activeobjcetpattern;

/**********************************
 * @author zhang zhao lin
 * @date 2021年08月04日 22:49
 * @Description
 **********************************/
public class MakeStringRequest extends MethodRequest {

    private int count;
    private char filterChar;

    public MakeStringRequest(Servant servant, FutureResult futureResult, int count, char filterChar) {
        super(servant, futureResult);
        this.count = count;
        this.filterChar = filterChar;
    }

    @Override
    public void execute() {
        Result result = servant.makeString(count, filterChar);
        futureResult.setResult(result);
    }
}
