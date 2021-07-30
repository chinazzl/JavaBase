package org.javabase.queue;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package org.javabase.queue
 * @Description:
 * @Date: 2021/6/30 15:58
 */
public interface DefaultInterface {

    default void test() {
        System.out.println("default result.");
    }
}
