package com.realm.test.realm;

import com.realm.test.BaseTest;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import java.util.stream.Stream;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.realm.test.realm
 * @Description:
 * @Date: 2021/4/28 14:21
 */
public class MutiRealmTest extends BaseTest {

    @Test
    public void test() {
        login("classpath:shiro-mutirealm.ini","zhang","123");
        Subject subject = getSubject();
        System.out.println(subject.getPrincipal());
        PrincipalCollection principals = subject.getPrincipals();
        System.out.println(principals.getRealmNames());
        Stream.of(principals).forEach(t -> {
            System.out.println(t.getPrimaryPrincipal());
        });
    }


}
