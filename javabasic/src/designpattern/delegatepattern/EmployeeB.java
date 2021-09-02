package designpattern.delegatepattern;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.delegatepattern
 * @Description:
 * @Date: 2021/8/5 16:23
 */
public class EmployeeB implements IEmployee {
    @Override
    public void working(String command) {
        System.out.println("EmployeeB works " + command);
    }
}
