package SE8.LambdaPac;

import java.util.Calendar;

/**
 *
 */
public class Lamdba_Demo1 {

    @FunctionalInterface
    public interface IsLambdamFunction{

        void isTrue();
    }


    public static void main(String[] args) {
        /*Runnable r1 = () -> System.out.println("Hello World 1");

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World 2");
            }
        };
        
        process(r1);
        process(r2);
        process(() -> System.out.priAntln("Hello World 3"));*/

        //IsLambdamFunction 的实现类
        IsLambdamFunction ilf = () -> System.out.println("true");
        ilf.isTrue();

        Object obj=new Object();
        System.out.println("begin");
    }

    private static void process(Runnable r) {
//        System.out.println("Hello World 3");
    }
}
