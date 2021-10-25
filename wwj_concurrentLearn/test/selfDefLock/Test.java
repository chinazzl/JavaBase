package selfDefLock;

import java.util.stream.IntStream;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package selfDefLock
 * @Description:
 * @Date: 2021/6/23 16:42
 */
public class Test {
    public static void main(String[] args) {
//        Thread thread = n
//        thread.start();

        IntStream.rangeClosed(0,100).forEach(i -> {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName());
                } catch (Exception e) {
                    System.out.println("error");
                } finally {
                    System.out.println("finally");
                }

            }).start();
        });
    }

}
