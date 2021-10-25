package spring.learn.beantest;

import spring.learn.po.User;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package spring.learn.beantest
 * @Description:
 * @Date: 2021/10/9 10:56
 */
public class Simple {

    private User user;

    public Simple(User user) {
        this.user = user;
    }

    public void getUser() {
        System.out.println(user.getAge());
    }
}
