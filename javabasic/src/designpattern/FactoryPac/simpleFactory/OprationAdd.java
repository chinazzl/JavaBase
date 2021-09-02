package designpattern.FactoryPac.simpleFactory;

//加法
public abstract class OprationAdd extends Opration{
    @Override
    protected double getResult() {
        return getValue1() + getValue2();
    }
}
