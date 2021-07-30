package number;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/6/2 22:33
 * @Modified By：
 */
public class LearnBigDecimal {

    public static void main(String[] args) {
        BigDecimal douDecimal = new BigDecimal(0.1);
        BigDecimal stringDecimal = new BigDecimal("0.1");
        System.out.println(douDecimal);
        System.out.println(stringDecimal);

        double dval = 12323.2755116;
//        BigDecimal bd = new BigDecimal(df);
//        double v = bd.setScale(2, RoundingMode.HALF_UP).doubleValue();
//        System.out.println(v);
        DecimalFormat df = new DecimalFormat("#.00");
        String format = df.format(dval);
        System.out.println(format);

    }
}
