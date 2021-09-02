package designpattern.FactoryPac.FactoryMethod.FactoryMethod;

//加法工厂类
public class AddFactory implements IFactory {

    @Override
    public Opration creatOpration() {
        return new AddOperation();
    }
}
