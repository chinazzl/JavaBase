package designpattern.strategypattern;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.strategypattern
 * @Description:
 * @Date: 2021/8/6 10:04
 */
public class PayState {

    private int paystate;

    private String payResult;

    private String payAmount;

    public PayState(int paystate, String payResult, String payAmount) {
        this.paystate = paystate;
        this.payResult = payResult;
        this.payAmount = payAmount;
    }

    public PayState(int paystate, String payResult) {
        this.paystate = paystate;
        this.payResult = payResult;
    }

    @Override
    public String toString() {
        return "支付状态 【" + paystate +"】" + payResult + "，交易详情：" + payAmount;
    }
}
