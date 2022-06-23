package favor.testPackage;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Julyan
 * @version V1.0
 * @Description:
 * @Date: 2022/5/6 16:24
 */
public class DateTest {

    public static void main(String[] args) {
        SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date nowDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        Date updateDate1 = calendar.getTime();
        System.out.println("变化后的时间1="+sdf.format(updateDate1));
        //1 月份的设置，0为1月份算起，
        calendar.set(2017, 10, 10, 10, 10, 10);
        Date updateDate2 = calendar.getTime();
        System.out.println("变化后的时间2="+sdf.format(updateDate2));
        //2 如果月日时分秒字段 超过最大数，会向前个进制一位，如月份设置成12，则年份就+1，月份变成1月份
        calendar.set(2017, 12, 10, 10, 10, 10);
        Date updateDate3 = calendar.getTime();
        System.out.println("变化后的时间3="+sdf.format(updateDate3));
        //2017年11月份最多有30日，设置成31，进制到月
        calendar.set(2017, 10, 31, 10, 10, 10);
        Date updateDate4 = calendar.getTime();
        System.out.println("变化后的时间4="+sdf.format(updateDate4));
        calendar.set(2017, 10, 31, 10, 10, 10);
    }
}
