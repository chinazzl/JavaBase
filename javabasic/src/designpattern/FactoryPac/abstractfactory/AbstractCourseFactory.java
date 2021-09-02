package designpattern.FactoryPac.abstractfactory;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.FactoryPac.abstractfactory
 * @Description: 抽象工厂
 * @Date: 2021/7/7 17:03
 */
public abstract class AbstractCourseFactory {

    abstract ILearnIO getJavaIO();

    abstract ILearnThread getJavaThread();
}
