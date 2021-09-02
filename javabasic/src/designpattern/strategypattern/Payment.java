package designpattern.strategypattern;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.strategypattern
 * @Description:
 * @Date: 2021/8/6 10:01
 */
public abstract class Payment {

    public abstract String getName();

    public abstract double queryBalance(String uid);

    public PayState pay(String payName, double amount) {
        if (queryBalance(payName) < amount) {
            return new PayState(500, "余额不足");
        }
        return new PayState(200, "支付成功", payName + "余额：" + amount);
    }
}
