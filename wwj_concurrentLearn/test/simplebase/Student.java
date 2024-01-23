package simplebase;

import base.Person;
import base.common.CommonA;

import java.util.concurrent.TimeUnit;

/**
 * @author Julyan
 * @version V1.0
 * @Title: JavaBase
 * @Package simplebase
 * @Description:
 * @Date: 2021/12/3 14:15
 */
public class Student extends Person {


    public static void main(String[] args) {
        //CommonA commonA = new CommonA();
//        commonA.c1();  default   failure
//        commonA.c2();  protected failure

        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                sleep(2);
            }
        });
        thread.start();
        System.out.println("--------------------------------");
    }

    private static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
