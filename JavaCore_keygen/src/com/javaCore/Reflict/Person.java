package com.javaCore.Reflict;

public class Person {

    //成员变量
  private String name;


  public String name_1;

    public Person() {
    }

    public Person(String name, String name_1) {
        this.name = name;
        this.name_1 = name_1;
    }

    public void getPersonMethod(){
      System.out.println("this is persion");
  }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
