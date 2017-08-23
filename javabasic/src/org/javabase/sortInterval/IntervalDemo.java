package org.javabase.sortInterval;

import java.util.*;

/**
 * Created by Felix on 2017/8/18.
 */
public class IntervalDemo {
    public static void main(String[] args) {
        List list = new ArrayList();
        List list1 = new ArrayList();
        list.add("1");
        list.add("10");
        list.add("20");
        list.add("30");
        list.add("31");
        list.add("40");
        Boolean interval = Interval(list1, "21", "25");
        System.out.println(interval);
    }

    private static Boolean Interval(List list,String min, String max) {
        Boolean flag = false;
        List numList = new ArrayList();
        numList.add(min);
        numList.add(max);
        Set set = new HashSet();
        set.addAll(numList);
        if (set.size() == numList.size()){
            Collections.sort(numList);
            int minIndex = numList.indexOf(min);
            if (numList.get(minIndex+1) == max){
                flag = true;
            }
        }
        return flag;

    }


}
