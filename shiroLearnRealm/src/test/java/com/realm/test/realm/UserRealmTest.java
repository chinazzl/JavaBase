package com.realm.test.realm;

import com.realm.test.BaseTest;
import org.apache.shiro.authc.UnknownAccountException;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.realm.test.realm
 * @Description:
 * @Date: 2021/4/28 10:02
 */
public class UserRealmTest extends BaseTest {

    @Test
    public void baseUserRealmTest() {
        login("classpath:shiro.ini","zhang","123");
        Assert.assertTrue(getSubject().isAuthenticated());
    }

    @Test(expected = UnknownAccountException.class)
    public void testLoginFailWithUnknownUsername() {
        login("classpath:shiro.ini", u1.getUsername() + "1", password);
    }

    /**
     * 测试失败重试次数
     */
    @Test
    public void testLoginFailWithLimitRetryCount() {
        for(int i = 1; i <= 6; i++) {
            try {
                login("classpath:shiro.ini", u3.getUsername(), "1231");
            } catch (Exception e) {/*ignore*/}
        }
        login("classpath:shiro.ini", u3.getUsername(), "123");

        //需要清空缓存，否则后续的执行就会遇到问题(或者使用一个全新账户测试)

    }
}
