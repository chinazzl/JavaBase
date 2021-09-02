package designpattern.proxypattern.dynasticproxy.cglibproxy;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.proxypattern.dynasticproxy.cglibproxy
 * @Description:
 * @Date: 2021/8/5 14:11
 */
public class CglibTest {
    public static void main(String[] args) {
        CglibEntity instance = (CglibEntity) new CGLibMeipo().getInstance(CglibEntity.class);
        instance.findLove();
    }
}
