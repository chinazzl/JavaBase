package designpattern.strategypattern;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.strategypattern
 * @Description:
 * @Date: 2021/8/6 10:46
 */
public class StrategyPatternTest {
    public static void main(String[] args) {
        Order order = new Order("1","sx12222211",12);
        PayState pay = order.pay(PayStrategy.SAMSUNG_PAY);
        System.out.println(pay);
    }
}
