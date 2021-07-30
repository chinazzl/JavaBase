package designpattern.proxypattern.dynasticproxy.jdkproxy;


import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.proxypattern.dynasticproxy.jdkproxy
 * @Description:
 * @Date: 2021/7/29 15:24
 */
public class JDKProxyTest {
    public static void main(String[] args) {
        FileOutputStream fos = null;
        try {
            Person person = (Person) new MeiPoJDKProxy().getInstance(new Custom());
            person.findLove();

            File file = new File("C:\\Users\\Julyan\\Desktop\\科学\\$Proxy0.class");
            fos = new FileOutputStream(file);
            byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{Person.class});
            fos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
