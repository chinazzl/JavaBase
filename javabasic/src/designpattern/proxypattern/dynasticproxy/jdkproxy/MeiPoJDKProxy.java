package designpattern.proxypattern.dynasticproxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.proxypattern.dynasticproxy.jdkproxy
 * @Description:
 * @Date: 2021/7/29 14:46
 */
public class MeiPoJDKProxy implements InvocationHandler {

    private Object target;

    public Object getInstance(Object target) {
        this.target = target;
        Class<?> clazz = target.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }


    /**
     * InvocationHandler 是代理实例的调用处理程序 实现的接口。
     * <p>
     * 每个代理实例都具有一个关联的调用处理程序。对代理实例调用方法时，将对方法调用进行编码并将其指派到它的调用处理程序的 invoke 方法。
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object object = method.invoke(this.target,args);
        after();
        return object;
    }

    private void before() {
        System.out.println("invoke do something before.");
    }

    private void after() {
        System.out.println("invoked do something after.");
    }
}
