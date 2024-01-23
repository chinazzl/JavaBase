package designpattern.prototype.regular;

import java.util.ArrayList;
import java.util.List;

/**********************************
 * @author zhang zhao lin
 * @date 2023年04月24日 23:24
 * @Description
 **********************************/
public class PrototypeTest {
    public static void main(String[] args) {
        ConcretePrototypeA concretePrototypeA = new ConcretePrototypeA();
        concretePrototypeA.setAge(18);
        concretePrototypeA.setName("prototype");
        List<String> hobbies = new ArrayList<>();
        concretePrototypeA.setHobbies(hobbies);
        System.out.println(concretePrototypeA);

        Client client = new Client(concretePrototypeA);
        ConcretePrototypeA concretePrototypeA1 = (ConcretePrototypeA) client.startClient(concretePrototypeA);
        System.out.println("克隆对象的引用类型地址值：" + concretePrototypeA1.getHobbies());
        System.out.println("原型对象的引用类型地址值：" + concretePrototypeA.getHobbies());
        System.out.println("对象地址值比对：" + (concretePrototypeA1.getHobbies() == concretePrototypeA.getHobbies()));
    }
}
