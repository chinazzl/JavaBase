package designpattern.observepattern;

import java.util.Observable;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.observepattern
 * @Description:
 * @Date: 2021/9/7 16:37
 */
public class GPer extends Observable {

    private static final String name = "Gper 生态圈";

    private static GPer gPer = null;

    private GPer() {
    }

    public static GPer getInstance() {
        if (gPer == null) {
            gPer = new GPer();
        }
        return gPer;
    }

    public void publishEvent(Question question) {
        System.out.println(question.getUsername() + "在" + name + "上提出问题");
        setChanged();
        notifyObservers(question);
    }


}
