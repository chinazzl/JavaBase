package designpattern.FactoryPac.simpleFactory;

/**
 * 简单工厂：
 * 1. 一个调用者创建一个对象，只要知道其名称
 * 2。 屏蔽产品的具体实现，调用者只关心产品的接口
 * Factory:这是本模式的核心,含有一定的商业逻辑和判断逻辑。在java中它往往由 一个具体类实现。（OperationFactory）
 *
 * Product:它一般是具体产品继承的父类或者实现的接口。在java中由接口或者抽象类来实现。（Operation）
 *
 * ConcreteProduct:工厂类所创建的对象就是此角色的实例。在java中由一个具体类实现。 来用类图来清晰的表示下的它们之间的关系（OperationAdd\OperationSub等）
 * 关系图如下：
 */
public abstract class Opration {
    private double value1 = 0;

    private double value2 = 0;

    public double getValue1() {
        return value1;
    }

    public void setValue1(double value1) {
        this.value1 = value1;
    }

    public double getValue2() {
        return value2;
    }

    public void setValue2(double value2) {
        this.value2 = value2;
    }

    protected abstract double getResult();
}

