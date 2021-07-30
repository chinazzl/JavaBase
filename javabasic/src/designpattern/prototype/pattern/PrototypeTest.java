package designpattern.prototype.pattern;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.prototype.pattern
 * @Description:
 * @Date: 2021/7/19 16:52
 */
public class PrototypeTest {

    public static void main(String[] args) throws IOException {
        ConcretePrototypeA concretePrototypeA = new ConcretePrototypeA("original_A");
        System.out.println(concretePrototypeA);
        ConcretePrototypeA prototypeA = concretePrototypeA.clone();
        prototypeA.desc = "clone_A";
        System.out.println(concretePrototypeA.hashCode() == prototypeA.hashCode());
        System.out.println(prototypeA);
        InputStream i = null;
        IOUtils.toString(i);
    }
}
