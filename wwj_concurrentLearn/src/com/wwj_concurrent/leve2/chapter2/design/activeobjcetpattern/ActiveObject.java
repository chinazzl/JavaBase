package com.wwj_concurrent.leve2.chapter2.design.activeobjcetpattern;

/**********************************
 * @author zhang zhao lin
 * @date 2021年07月22日 22:22
 * @Description
 **********************************/
public interface ActiveObject {

    Result makeString(int count,char filterChar);

    void displayString(String text);
}
