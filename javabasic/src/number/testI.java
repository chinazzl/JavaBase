package number;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/10/22 16:54
 * @Modified By：
 * i++ 和 ++i 区别
 */
public class testI {
    private static Object obj = new Object();

    public static void main(String[] args) {
        // 1 进入操作数栈然后进入 局部变量表赋值为 i 5
//        int i = 1;
//        i = i++;
//        int j = i++;
//        int k = i + ++i * i++;
//        System.out.println("i=" + i);
//        System.out.println("j=" + j);
//        System.out.println("k=" + k);
//        System.out.println("main start");

//        new Thread(() -> {
//            synchronized (obj) {
//                try {
//                    obj.wait();
//                    System.out.println("over");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                obj.notify();
//            }
//
//        }).start();
//
//        new Thread(() -> {
//                synchronized (obj) {
//                    obj.notify();
//                }
//        }).start();

        String regex = "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}";
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher("192.256.1.1");
        System.out.println(matcher.matches());

    }
}
