package designpattern.observepattern;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.observepattern
 * @Description:
 * @Date: 2021/9/7 17:08
 */
public class ObserveTest {

    public static void main(String[] args) {
        GPer gPer = GPer.getInstance();
        Teacher teacher = new Teacher("Tom");
        gPer.addObserver(teacher);
        Question question = new Question();
        question.setUsername("Jerry");
        question.setQuestionContent("学校叫什么？");
        gPer.publishEvent(question);
    }
}
