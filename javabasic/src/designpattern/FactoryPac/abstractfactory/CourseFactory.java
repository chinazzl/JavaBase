package designpattern.FactoryPac.abstractfactory;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.FactoryPac.abstractfactory
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
