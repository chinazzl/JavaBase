package com.wwj_concurrent.leve2.chapter2.design.activeobjcetpattern;

/**********************************
 * @author zhang zhao lin
 * @date 2021年07月22日 22:48
 * @Description
 **********************************/
public class Servant implements ActiveObject{

    @Override
    public Result makeString(int count, char filterChar) {
        char[] charsArray = new char[count];
        for (int i = 0; i < charsArray.length; i++) {
            charsArray[i] = filterChar;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String result = new String(charsArray);
        return new RealResult(result);
    }

    @Override
    public void displayString(String text) {
        System.out.println("display the text ==> " + text);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
