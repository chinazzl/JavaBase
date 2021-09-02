package designpattern.proxypattern.dynasticproxy.cglibproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.proxypattern.dynasticproxy.cglibproxy
 * @Description:
 * @Date: 2021/8/5 13:54
 */
public class CGLibMeipo implements MethodInterceptor {

    public Object getInstance(Class<?> clazz) {
        Enhancer c = new Enhancer();
        //要把哪个设置为即将生成的新类的父类
        c.setSuperclass(clazz);
        c.setCallback(this);
        return c.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object obj = methodProxy.invokeSuper(o, objects);
        after();
        return obj;
    }

    private void before() {
        System.out.println("do somethings before.");
    }

    private void after() {
        System.out.println("do somethings after.");
    }
}
