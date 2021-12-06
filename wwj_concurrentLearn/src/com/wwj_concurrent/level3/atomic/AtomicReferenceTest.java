package com.wwj_concurrent.level3.atomic;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicReference;

/**********************************
 * @author zhang zhao lin
 * @date 2021年10月07日 23:09
 * @Description
 **********************************/
public class AtomicReferenceTest {

    public static void main(String[] args) {
        Simple s = new Simple("Tom", 123);
        AtomicReference<Simple> atomicReference = new AtomicReference<>(s);
        boolean b = atomicReference.compareAndSet(s, new Simple("hehehe", 333));
        System.out.println(b + " reference => " + atomicReference.get());

        JButton jButton = new JButton("asdf");

        String sa = "111;";
        //使用 封装类 在匿名内部类中修改数据
        ObjectWrapper<String> saObject = new ObjectWrapper<>(sa);

        //使用AtomReference 修改内部类数据
        AtomicReference<String> atomStringReference = new AtomicReference<>(sa);


        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atomStringReference.set(new String("fff"));
            }
        });
    }

    static class ObjectWrapper<T> {
        private T t;

        public ObjectWrapper(T obj) {
            t = obj;
        }

        public T getT() {
            return t;
        }

        public void setT(T t) {
            this.t = t;
        }
    }

    static class Simple {
        private String name;

        private Integer password;

        public Simple(String name, Integer password) {
            this.name = name;
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getPassword() {
            return password;
        }

        public void setPassword(Integer password) {
            this.password = password;
        }

        @Override
        public String toString() {
            return "Simple{" +
                    "name='" + name + '\'' +
                    ", password=" + password +
                    '}';
        }
    }
}
