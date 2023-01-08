package guavaUtil;

import com.google.common.base.Throwables;

/**
 * @author Julyan
 * @version V1.0
 * @Date: 2022/11/24 21:08
 * @Description: Throwable类提供了相关的Throwable接口的实用方法。
 */
public class GuavaThrowable {

    public static void main(String[] args) {
        try {
            int i = 1/0;
        }catch (Exception e) {
            Throwables.getCausalChain(e).forEach(System.out::println);
            System.out.println(Throwables.getStackTraceAsString(e));
        }
    }
}
