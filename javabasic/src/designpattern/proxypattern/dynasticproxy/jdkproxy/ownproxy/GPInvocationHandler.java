package designpattern.proxypattern.dynasticproxy.jdkproxy.ownproxy;

import java.lang.reflect.Method;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.proxypattern.dynasticproxy.jdkproxy.ownproxy
 * @Description:
 * @Date: 2021/7/30 14:08
 */
public interface GPInvocationHandler {
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable;

}
