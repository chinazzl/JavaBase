package designpattern.templatepattern;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.templatepattern
 * @Description:
 * @Date: 2021/8/6 14:47
 */
public class templatePtternTest {
    public static void main(String[] args) {
        NetworkCourse javaCourse = new JavaCourse();
        javaCourse.createCourse();

        NetworkCourse bigDataCourse = new BigDataCourse(true);
        bigDataCourse.createCourse();
    }
}
