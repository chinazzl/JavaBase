package designpattern.FactoryPac.FactoryMethod.FactoryMethod;

public class MinFactory implements IFactory {
    @Override
    public Opration creatOpration() {
        return  new MInOpration();
    }
}
