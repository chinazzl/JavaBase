package designpattern.decortorpattern;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.decortorpattern
 * @Description:
 * @Date: 2021/9/3 16:58
 */
public class BreadCake extends BaseCake {

    @Override
    public String getMsg() {
        return "油条";
    }

    @Override
    public double getPrice() {
        return 5;
    }
}
