package designpattern.strategypattern;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.strategypattern
 * @Description:
 * @Date: 2021/8/6 10:32
 */
public class Order {

    private String payName;

    private String orderId;

    private double amount;

    public Order(String payName, String orderId, double amount) {
        this.payName = payName;
        this.orderId = orderId;
        this.amount = amount;
    }

    public PayState pay() {
        return pay(PayStrategy.DEFAULT_PAY);
    }

    public PayState pay(String payName) {
        Payment payment = PayStrategy.get(payName);
        System.out.println("欢迎使用" + payment.getName());
        System.out.println("本次交易金额为：" + amount);
        return payment.pay(payName, amount);
    }
}
