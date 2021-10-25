package designpattern.decortorpattern;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.decortorpattern
 * @Description:
 * @Date: 2021/9/3 17:11
 */
public class SausageDecorotor extends BreadCakeDecortor {

    public SausageDecorotor(BreadCake breadCake) {
        super(breadCake);
    }

    @Override
    public String getMsg() {
        return super.getMsg() + "+1根 香肠";
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 2;
    }
}
