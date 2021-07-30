package designpattern.proxypattern.staticproxy;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.proxypattern.staticproxy
 * @Description:
 * @Date: 2021/7/22 17:06
 */
public class DynamicDBProxy implements IOrderService {

    private IOrderService orderService;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

    public DynamicDBProxy() {
        this.orderService = new OrderServiceImpl();
    }

    @Override
    public void createOrder(Order order) {
        before();
        Long dbrouter = order.getCreateTime();
        int ctime = Integer.parseInt(sdf.format(new Date(dbrouter)));
        System.out.println("自动代理自动分配DB到" + dbrouter + "数据源");
        DynamicDataSourceEntity.set(ctime);
        orderService.createOrder(order);
        after();

    }

    private void before() {

        System.out.println("Before change DB something");
    }

    private void after() {
        System.out.println("After change DB something");

    }
}
