package designpattern.proxypattern.dynasticproxy.jdkproxy;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.proxypattern.dynasticproxy.jdkproxy
 * @Description:
 * @Date: 2021/7/29 15:23
 */
public class Custom implements Person {
    @Override
    public void findLove() {
        System.out.println("A");
        System.out.println("B");
        System.out.println("C");
    }
}
