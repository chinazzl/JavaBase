package designpattern.adapterpattern;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.adapterpattern
 * @Description: 老 版本的登陆接口类
 * @Date: 2021/9/2 17:10
 */
public class MessageService {

    public ResultMsg login(String username, String password) {
        return new ResultMsg(200,"登陆成功","当前登陆人：" + username);
    }

    public ResultMsg register(String username, String password) {
        return new ResultMsg(200,"注册成功",new Member());
    }
}
