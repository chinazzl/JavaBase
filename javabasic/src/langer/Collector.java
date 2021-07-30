package langer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Felix on 2017/8/3.
 */
public class Collector {
    public static void main(String[] args) {
       /* List<List<String>> fatherList = new LinkedList<>();
        List<String> childList = new ArrayList<>();
        childList.add("before add child list");
        fatherList.add(childList);
        childList.add("after add child list");
        for (List l: fatherList) {
            System.out.println(l);
        }*/

        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH,-1);
        c.getMinimum(Calendar.DATE);

    }
}
