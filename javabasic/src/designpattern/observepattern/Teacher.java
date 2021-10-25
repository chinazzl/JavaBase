package designpattern.observepattern;

import java.util.Observable;
import java.util.Observer;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.observepattern
 * @Description:
 * @Date: 2021/9/7 17:00
 */
public class Teacher implements Observer {

    private String name;

    public Teacher(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        GPer gPer = (GPer) o;
        Question question = (Question) arg;
        System.out.println("==========================");
        System.out.println(this.name + " 老师你好，这有一个问题向您请教一下，问题：\n" +
                question.getQuestionContent() + "\n" +
                "提问者：" + question.getUsername());
    }
}
