package org.javabase.threadLearn.beRunable;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class UnSafe {
    public static void main(String[] args) {
        Vector list = new Vector();
//        List list = new ArrayList();
        int a = 1;
        int b = 2;
        Th th = new Th();
        Th th2 = new Th();
        Thread thread = new Thread(th);
        Thread thread1 = new Thread(th2);
        thread.start();
        thread1.start();

    }
}

 class Th implements Runnable{

    private int num ;

    private Vector list  ;
//
//     public Th(int num, Vector list) {
//         this.num = num;
//         this.list = list;
//     }

     public int Array( Vector list , int nums){
         list.add(0,nums);
         return list.size();
     }

     @Override
     public void run() {
         int array = Array(list, num);
         System.out.println("szie: " + array);
     }

 }