package designpattern.templatepattern;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.templatepattern
 * @Description:
 * @Date: 2021/8/6 14:44
 */
public class BigDataCourse extends NetworkCourse {

    private final boolean needHomework;

    public BigDataCourse(boolean needHomework) {
        this.needHomework = needHomework;
    }

    @Override
    void checkHomeWork() {
        System.out.println("检查 BigDataCourse ...");
    }

    @Override
    protected boolean needHomework() {
        return this.needHomework;
    }
}
