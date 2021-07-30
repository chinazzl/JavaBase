package designpattern.proxypattern.staticproxy;

import java.util.Date;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.proxypattern.staticproxy
 * @Description:
 * @Date: 2021/7/29 10:39
 */
public class StaticProxyMain {

    public static void main(String[] args) {
        Order order = new Order();
        order.setCreateTime(new Date().getTime());
        IOrderService orderService = new DynamicDBProxy();
        orderService.createOrder(order);

    }
}
