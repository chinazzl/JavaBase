package designpattern.proxypattern.dynasticproxy.jdkproxy.ownproxy;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.proxypattern.dynasticproxy.jdkproxy.ownproxy
 * @Description:
 * @Date: 2021/8/4 10:39
 */
public class GPMeipo implements GPInvocationHandler{
    //被代理对象的引用
    private Object target;

    public Object getInstance(Object target) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        this.target = target;
        Class<?> clazz = target.getClass();
        return GPProxy.newProxyInstance(new GPClassLoader(),clazz.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        method.invoke(proxy,args);
        after();
        return null;
    }

    public void before() {
        System.out.println("do something before.");
        System.out.println("ba la ba la ba la");
    }

    public void after() {
        System.out.println("do something after.");
    }
}
