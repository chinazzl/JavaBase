package designpattern.prototype.pattern;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.prototype.pattern
 * @Description:
 * @Date: 2021/7/19 16:43
 */
public class ConcretePrototypeA implements IPrototype<ConcretePrototypeA>{
    public String desc;

    public ConcretePrototypeA(String desc) {
        this.desc = desc;
    }

    @Override
    public ConcretePrototypeA clone() {
        return new ConcretePrototypeA(desc);
    }

    @Override
    public String toString() {
        return "ConcretePrototypeA{" +
                "desc='" + desc + '\'' +
                '}';
    }
}
