package designpattern.proxypattern.staticproxy;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.proxypattern.staticproxy
 * @Description:
 * @Date: 2021/7/29 10:12
 */
public class DynamicDataSourceEntity {
    private final static String DEFAULT_DATA_SOURCE = "DEFAULT_DATA_SOURCE";

    private static final ThreadLocal<String> local = new ThreadLocal<>();

    public static void clear() {
        local.remove();
    }
    //获取数据源
    public static String get() {
        return local.get();
    }

    public static void set(String source) {
        local.set(source);
    }

    public static void setDefultDataSource() {
        local.set(DEFAULT_DATA_SOURCE);
    }

    public static void set(int year) {
        local.set("DB_" + year);
    }

}
