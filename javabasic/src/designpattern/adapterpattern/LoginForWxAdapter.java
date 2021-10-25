package designpattern.adapterpattern;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.adapterpattern
 * @Description:
 * @Date: 2021/9/3 9:52
 */
public class LoginForWxAdapter  implements LoginAdapter{
    @Override
    public Boolean support(Object adapter) {
        return adapter instanceof LoginForWxAdapter;
    }

    @Override
    public ResultMsg login(String username, Object adapter) {
        return null;
    }
}
