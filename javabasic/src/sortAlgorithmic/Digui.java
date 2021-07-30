package sortAlgorithmic;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Digui {
    public static void main(String[] args) {
        System.out.println("吓得我抱起了");
        digui(2);
//        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
//        service.scheduleWithFixedDelay(new Thread(),0,1, TimeUnit.SECONDS);
    }

    private static void digui(int i){
       if(i <= 0){
           System.out.print("我的小鲤鱼");
       }else{
           digui(--i);

       }
        System.out.print("的我" + i);
    }
}
