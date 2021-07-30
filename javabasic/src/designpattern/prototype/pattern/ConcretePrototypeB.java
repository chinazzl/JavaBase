package designpattern.prototype.pattern;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.prototype.pattern
 * @Description:
 * @Date: 2021/7/19 16:47
 */
public class ConcretePrototypeB implements IPrototype<ConcretePrototypeB> {
    private String desc;

    public ConcretePrototypeB(String desc) {
        this.desc = desc;
    }

    @Override
    public ConcretePrototypeB clone() {
        return new ConcretePrototypeB(desc);
    }
}
