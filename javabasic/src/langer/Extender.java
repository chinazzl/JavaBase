package langer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/6/4 23:19
 * @Modified By：内部类
 */
public class Extender {
    class emplyee extends Thread {
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

    class Teacher extends emplyee {

        public void priintT() {
            System.out.println("child name" + name);
        }
    }

    class Coder extends emplyee {
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
        List<? extends emplyee> emplyees = new ArrayList<>();
        Extender extender = new Extender();
        Extender.Teacher t = extender.new Teacher();
        Extender.Coder coder = extender.new Coder();

        List<Teacher> teachers = new ArrayList<>();
        teachers.add(t);
        System.out.println(updateEmplyee(teachers));

        //泛型的下限 代表只能是emplyee 的本身或者子类
        List<? super emplyee> l = new ArrayList<>();
        l.add(coder);
        l.add(t);

    }

    //<? extends emplyee> 代表泛型的上限，如果放在 定义的时候则会报错
    public static emplyee updateEmplyee(List<? extends emplyee> list) {
        return list.get(0);
    }
}
