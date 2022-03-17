package org.testdrools.entity;

/**
 * @author Julyan
 * @version V1.0
 * @Description:
 * @Date: 2022/3/16 11:09
 */
public class Order {
    //订单原始价格，即优惠前价格
    private Double originalPrice;
    //订单真实价格，即优惠后价格
    private Double realPrice;

    public Order(Double originalPrice, Double realPrice) {
        this.originalPrice = originalPrice;
        this.realPrice = realPrice;
    }

    public String toString() {
        return "Order{" +
                "originalPrice=" + originalPrice +
                ", realPrice=" + realPrice +
                '}';
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Double getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Double realPrice) {
        this.realPrice = realPrice;
    }
}
