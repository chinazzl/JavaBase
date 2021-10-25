package designpattern.adapterpattern;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.adapterpattern
 * @Description:
 * @Date: 2021/9/3 9:58
 */
public interface IPassportThirdAdapter {

    ResultMsg loginWx(String wxName);

    ResultMsg loginQQ(String id);

    ResultMsg registerAndLogin(String username, String password);
}
