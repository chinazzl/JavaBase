package designpattern.delegatepattern;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.delegatepattern
 * @Description:
 * @Date: 2021/8/5 16:26
 */
public class Boss {

    public void dispatchLeaderWork(String command, Leader leader) {
        leader.working(command);
    }
}
