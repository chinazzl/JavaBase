package designpattern.proxypattern.dynasticproxy.jdkproxy.ownproxy;

import designpattern.proxypattern.dynasticproxy.jdkproxy.Custom;
import designpattern.proxypattern.dynasticproxy.jdkproxy.Person;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.proxypattern.dynasticproxy.jdkproxy.ownproxy
 * @Description:
 * @Date: 2021/8/4 10:47
 */
public class OwnJDKProxyTest {
    public static void main(String[] args) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        Person person = (Person) new GPMeipo().getInstance(new Custom());
        System.out.println(person.getClass());
        person.findLove();
    }
}
