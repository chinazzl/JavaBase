package FactoryPac.FactoryMethod.FactoryMethod;

public class MInOpration extends Opration {

    @Override
    protected double min() {
        return getValue1() - getValue2();
    }
}
