package langer;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

public class AnnonyTest {

    public static void main(String[] args) {
        ArrayList list = new ArrayList(Collections.singleton(new int[]{1, 2, 3}));
        DecimalFormat decimalFormat = new DecimalFormat("##.00");
        double a = 1456.456;
        System.out.println(decimalFormat.format(a));
//        System.out.println("=====");
//        Integer c = 300;
//        Integer d = 300;
//        System.out.println(c == d);
        Integer i = 300;
        int j = 300;
         j = Integer.valueOf(i);
        System.out.println(i == j);


    }
}
