package designpattern.factorypattern.abstractfactory;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.factorypattern.abstractfactory
 * @Description:
 * @Date: 2021/7/7 17:12
 */
public class CourseFactory extends AbstractCourseFactory{

    @Override
    ILearnIO getJavaIO() {
        return new JavaCourseIO();
    }

    @Override
    ILearnThread getJavaThread() {
        return new JavaCourseThread();
    }


}
