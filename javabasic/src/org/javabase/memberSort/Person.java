package org.javabase.memberSort;

public abstract class Person {
    public static String name = " person static name";

    private String sex ;

    static {
        System.out.println("Person 有参静态代码块");
    }

    public Person(String sex) {
        System.out.println("Person 有参构造" + sex);
    }

    public Person() {
        System.out.println("父类中的无参构造");
    }


}
