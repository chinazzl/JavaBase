package designpattern.decortorpattern;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.decortorpattern
 * @Description: 油条装饰者 扩展
 * @Date: 2021/9/3 17:02
 */
public class BreadCakeDecortor extends BreadCake {

    private BreadCake breadCake;

    public BreadCakeDecortor(BreadCake breadCake) {
        this.breadCake = breadCake;
    }

    @Override
    public String getMsg() {
        return this.breadCake.getMsg();
    }

    @Override
    public double getPrice() {
        return this.breadCake.getPrice();
    }

}
