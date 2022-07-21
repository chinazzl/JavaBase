package org.testdrools.entity;

import java.io.Serializable;

/**
 * @author Julyan
 * @version V1.0
 * @Description:
 * @Date: 2022/3/18 17:02
 */
public class Student implements Serializable {
    private int id;

    private String name;

    private int age;

    private String log;

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", log='" + log + '\'' +
                '}';
    }
}
