package designpattern.factorypattern.abstractfactory;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.factorypattern.abstractfactory
 * @Description:
 * @Date: 2021/7/8 9:22
 */
public class AbstractFactoryMain {
    public static void main(String[] args) {
        CourseFactory courseFactory = new CourseFactory();
        courseFactory.getJavaIO().edit();
        courseFactory.getJavaThread().record();
    }
}
