package FactoryPac.FactoryMethod.FactoryMethod;

public class TestClazz {
    public static void main(String[] args) {
        /*
            加法工厂
         */
        AddFactory addFactory = new AddFactory();
        Opration opration = addFactory.creatOpration();
        opration.setValue1(1);
        opration.setValue2(2);

        /*
            减法工厂
         */
        MinFactory minFactory = new MinFactory();
        Opration minOperation = minFactory.creatOpration();
        minOperation.setValue1(1);
        minOperation.setValue2(3);


        System.out.println(opration.add());
        System.out.println(minOperation.min());
    }
}
