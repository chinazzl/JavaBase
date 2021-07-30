package SE8.Test;

import SE8.Apple;

import java.text.SimpleDateFormat;
import java.util.*;

public class TestJava8 {
    public static void main(String[] args) {
        List<Apple> list = new ArrayList();
//        Apple apple1 = new Apple();
//        apple1.setWeight(20.1);
//        list.add(apple1);
//
//        Apple apple2 = new Apple();
//        apple2.setWeight(23.1);
//        list.add(apple2);
//
//        Apple apple3 = new Apple();
//        apple3.setWeight(12.1);
//        list.add(apple3);
//
//        Apple apple4 = new Apple();
//        apple4.setWeight(0.1);
//        list.add(apple4);

        // before JavaSE8  Rooney
//        Collections.sort(list, new Comparator<Apple>() {
//            @Override
//            public int compare(Apple o1, Apple o2) {
//                return o1.getWeight().compareTo(o2.getWeight());
//            }
//
//            @Override
//            public boolean equals(Object obj) {
//                return false;
//            }
//        });

        // After JavaSE8
       /* list.sort(Comparator.comparing((Apple::getWeight)));

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getWeight());
        }

        String a = "123";
        String b = "1";
        String c = b + "23";

        System.out.println(a == c);*/

        long milles = new Date().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:00");
        Date date = new Date(milles);
        System.out.println(sdf.format(date));




    }
}
