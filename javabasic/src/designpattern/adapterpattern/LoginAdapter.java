package designpattern.adapterpattern;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.adapterpattern
 * @Description: 登陆适配器接口
 * @Date: 2021/9/3 9:46
 */
public interface LoginAdapter {

    Boolean support(Object adapter);

    ResultMsg login(String username, Object adapter);
}
