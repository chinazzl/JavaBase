package designpattern.delegatepattern;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.delegatepattern
 * @Description:
 * @Date: 2021/8/5 16:24
 */
public class Leader implements IEmployee {

    private final Map<String,IEmployee> dispatchers;

    public Leader() {
        dispatchers = new HashMap<>();
        dispatchers.put("加密", new EmployeeA());
        dispatchers.put("登陆", new EmployeeB());
    }

    @Override
    public void working(String command) {
        dispatchers.get(command).working(command);
    }
}
