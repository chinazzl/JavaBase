package designpattern.strategypattern;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.strategypattern
 * @Description:
 * @Date: 2021/8/6 10:19
 */
public class SamsungPay extends Payment {
    @Override
    public String getName() {
        return "Samsung Pay";
    }

    @Override
    public double queryBalance(String uid) {
        return 365;
    }
}
