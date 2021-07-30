package designpattern.factorypattern.abstractfactory;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.factorypattern.abstractfactory
 * @Description:
 * @Date: 2021/7/7 17:13
 */
public class JavaCourseIO implements ILearnIO {
    @Override
    public void edit() {
        System.out.println("learn Java Course.");
    }
}
