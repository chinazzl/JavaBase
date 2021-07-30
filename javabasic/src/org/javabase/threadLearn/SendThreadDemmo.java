package org.javabase.threadLearn;

import java.io.PrintWriter;
import java.util.Timer;
import java.util.TimerTask;

/**
 * aa: 自定义for循环
 * Timer 可以做延迟，定时任务
 */
public class SendThreadDemmo {

    public static void main(String[] args){

        String[] str1 = new String[6];
        str1[0] = "a";
        str1[2] = "b";

         aa:    for(int i = 0; i < 3; i++) {
             try{
                 String s1 = str1[i];
                 int j = i;
                 final Timer timer = new Timer();
                 timer.schedule(
                         new TimerTask() {
                             private int count = 0;
                             @Override
                             public void run() {
                                 System.out.println("str"+j+":" + s1.toString());
                                 count+=1;
                                 System.out.println("====count= " + count);
                                 if(count == 50){
                                     timer.cancel();
                                     System.out.println("***计划任务完成***");
                                 }
                             }
                         }
                 ,1000,2000);
                 System.out.println("//////s2= " +s1);
             }catch (Exception e){
                 e.printStackTrace();
                 continue aa;
             };
         }

    }
}
