package designpattern.templatepattern;

import java.net.NetworkInterface;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.templatepattern
 * @Description:
 * @Date: 2021/8/6 14:38
 */
public class JavaCourse extends NetworkCourse {

    @Override
    void checkHomeWork() {
        System.out.println("检查 JavaCourse ...");
    }
}
