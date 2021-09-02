package designpattern.delegatepattern;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.delegatepattern
 * @Description:
 * @Date: 2021/8/5 16:28
 */
public class DelegatePatternTest {

    public static void main(String[] args) {
        Boss boss = new Boss();
        boss.dispatchLeaderWork("加密",new Leader());
    }
}
