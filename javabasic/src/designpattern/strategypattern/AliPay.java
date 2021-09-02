package designpattern.strategypattern;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.strategypattern
 * @Description:
 * @Date: 2021/8/6 10:18
 */
public class AliPay extends Payment{

    @Override
    public String getName() {
        return "ALi Pay";
    }

    @Override
    public double queryBalance(String uid) {
        return 500.00;
    }
}
