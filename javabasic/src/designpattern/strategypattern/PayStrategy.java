package designpattern.strategypattern;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.strategypattern
 * @Description:
 * @Date: 2021/8/6 10:21
 */
public class PayStrategy {
    public static final String ALI_PAY = "ALi Pay";
    public static final String SAMSUNG_PAY = "Samsung Pay";
    public static final String WX_PAY = "WX Pay";
    public static final String DEFAULT_PAY = "Samsung Pay";

    private static final Map<String, Payment> paymentMap = new HashMap<>();

    static {
        paymentMap.put(ALI_PAY, new AliPay());
        paymentMap.put(SAMSUNG_PAY, new SamsungPay());
        paymentMap.put(WX_PAY, new WXPay());
    }

    public static Payment get(String payName) {
        if (paymentMap.containsKey(payName)) {
            return paymentMap.get(payName);
        }
        return paymentMap.get(DEFAULT_PAY);
    }

}
