package SE8.StreamPac.collector;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/12/29 17:14
 * @Modified By：
 * <p>
 * {
 * "rowno": "1",
 * "systemID": "1",
 * "appService": "xxx",
 * "systemName": "zzz",
 * "ipAddress": "11111",
 * "osTotal": null,
 * "kpiName": [
 * {
 * "error_id": 1,
 * "kpiName": "事务",
 * "type": "Oracle"
 * }
 * ]
 * }
 */
public class CollectorsTest {

    private static List<T> list = list = Arrays.asList(new T("100", "2", "3", "1"),
            new T("12", "5", "8", "2"),
            new T("3", "6", "9", "1"),
            new T("4", "7", "10", "2")
    );


    public static void main(String[] args) {

        Map<String, List<T>> collect = list.stream().collect(Collectors.groupingBy(T::getAlarmId));
        list.stream().forEach(t -> {
            t.setTlist(collect.get(t.getAlarmId()));
        });

        System.out.println(list);
        /*for (T t : list) {
            List<T> tlist = t.getTlist();
            for (T t1 : tlist) {
                System.out.println(t1.getError_id());
            }
        }*/
    }
}

class T {

    private String alarmId;

    private String error_id;

    private String kpiName;

    private String magicType;

    private List<T> tlist = new ArrayList<>();

    public T(String error_id, String kpiName, String magicType, String alarmId) {
        this.error_id = error_id;
        this.kpiName = kpiName;
        this.magicType = magicType;
        this.alarmId = alarmId;
    }

    public T(String error_id, String kpiName, String magicType) {
        this.error_id = error_id;
        this.kpiName = kpiName;
        this.magicType = magicType;
    }

    public String getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(String alarmId) {
        this.alarmId = alarmId;
    }

    public List<T> getTlist() {
        return tlist;
    }

    public void setTlist(List<T> tlist) {
        this.tlist = tlist;
    }

    public String getError_id() {
        return error_id;
    }

    public void setError_id(String error_id) {
        this.error_id = error_id;
    }

    public String getKpiName() {
        return kpiName;
    }

    public void setKpiName(String kpiName) {
        this.kpiName = kpiName;
    }

    public String getMagicType() {
        return magicType;
    }

    public void setMagicType(String magicType) {
        this.magicType = magicType;
    }
}