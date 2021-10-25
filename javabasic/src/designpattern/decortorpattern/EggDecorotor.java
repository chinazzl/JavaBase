package designpattern.decortorpattern;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.decortorpattern
 * @Description: 鸡蛋装饰者
 * @Date: 2021/9/3 17:06
 */
public class EggDecorotor extends BreadCakeDecortor {

    public EggDecorotor(BreadCake breadCake) {
        super(breadCake);
    }

    @Override
    public String getMsg() {
        return super.getMsg() + "+ 1个 鸡蛋";
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 1;
    }
}
