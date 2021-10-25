package designpattern.decortorpattern;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.decortorpattern
 * @Description:
 * @Date: 2021/9/3 17:16
 */
public class DecortotTest {
    public static void main(String[] args) {
        BreadCake breadCake = new BreadCake();
        breadCake = new EggDecorotor(breadCake);
        breadCake = new EggDecorotor(breadCake);
        breadCake = new SausageDecorotor(breadCake);
        System.out.println(breadCake.getMsg() + "总数：" + breadCake.getPrice());
    }
}
