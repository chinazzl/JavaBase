package org.javabase.memberSort;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TEst {
    public static void main(String[] args) throws ParseException {
      /*  Person p = new Student();
        System.out.println(p);*/

        String dstr = "2021/08/07 12:20:10";
        String dstr1 = "2021/08/07 12:20:11";

        Date d1 = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date parse = sdf.parse(dstr);
        System.out.println(parse.getTime());

        Date parse1 = sdf.parse(dstr1);
        System.out.println(parse1.getTime());

    }
}  
