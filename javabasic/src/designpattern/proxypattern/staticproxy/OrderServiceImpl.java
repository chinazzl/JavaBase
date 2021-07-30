package designpattern.proxypattern.staticproxy;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.proxypattern.staticproxy
 * @Description:
 * @Date: 2021/7/22 17:08
 */
public class OrderServiceImpl implements IOrderService{
    private OrderDao orderDao;

    public OrderServiceImpl() {
        this.orderDao = new OrderDao();
    }

    @Override
    public void createOrder(Order order) {
        System.out.println("创建 DB 订单成功");
        orderDao.insert(order);
    }
}
