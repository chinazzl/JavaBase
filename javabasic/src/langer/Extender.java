package langer;

import java.util.ArrayList;
import java.util.List;

import static baseline.CPUsage.t;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/6/4 23:19
 * @Modified By：内部类
 */
public class Extender {
    static class emplyee extends Thread {
        String name;

        public void printName() {
            System.out.println("parent method");
        }

        @Override
        public String toString() {
            return "emplyee{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    static class Teacher extends emplyee {

        public void priintT() {
            System.out.println("child name" + name);
        }
    }

    static class Coder extends emplyee {
        @Override
        public void printName() {
            System.out.println("coder...");
        }
    }


    public static void main(String[] args) {
       /* Extender extender = new Extender();
        Extender.Teacher t = extender.new Teacher();
        t.setName("zs");

        t.priintT();*/

        /**
         * <? extends T>：上界通配符
         *
         * 实例化时的类只能是定义时类本身或其子类，也就是说T是它的上界。
         * 上界通配符add失效（只能add null），可以get
         * 能放进去的对象类型其上界为实例化时指定的T：
         */
        List<? extends emplyee> emplyees = new ArrayList<Teacher>() {
            {
                Teacher t = new Teacher();
                add(t);
            }
        };
        Coder coder = new Coder();
        Teacher t = new Teacher();

        //泛型的下限 代表只能是emplyee 的本身或者子类
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(t);
        System.out.println(updateEmplyee(teachers));

        /**
         * 实例化时的类只能是定义时类本身或其父类，也就是说T是它的下界。
         *
         * 添加对象时，在实例化时添加和实例化后添加的对象限制不同（特别注意！）：
         * 在实例化时添加的对象只有一个限制：上界为实例化时指定的T。来看一个有趣的现象，即使定义时指定的T是Fruit，但实例化时可以用Fruit的邻居类：
         * 在实例化后添加的对象，其上界是定义时指定的T：
         */
        List<? super emplyee> l = new ArrayList<>();
        l.add(coder);
        // 是本身或是它的父类
        l.add(t);

    }

    //<? extends emplyee> 代表泛型的上限，如果放在 定义的时候则会报错
    public static emplyee updateEmplyee(List<? extends emplyee> list) {
        return list.get(0);
    }
}
