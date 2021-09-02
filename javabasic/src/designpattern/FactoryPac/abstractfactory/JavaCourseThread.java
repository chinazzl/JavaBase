package designpattern.FactoryPac.abstractfactory;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.FactoryPac.abstractfactory
 * @Description:
 * @Date: 2021/7/8 9:16
 */
public class JavaCourseThread implements ILearnThread {

    @Override
    public void record() {
        System.out.println("learn Thread Course.");
    }
}
