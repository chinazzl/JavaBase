package src.shirotest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.my.booter.Runner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package src.shirotest
 * @Description:
 * @Date: 2021/4/21 10:55
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Runner.class)
public class ShiroTest {

    @Test
    public void firstShiroTest() {
        //使用ini配置文件进行初始化 SecurityManager
        Factory<SecurityManager> factory =  new IniSecurityManagerFactory("classpath:shiro.ini");
        //得到securityManager实例，并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //得到subject以及创建用户名/密码身份 验证Token
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        //shiro 进行身份验证
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            //校验登陆失败
        }
        subject.logout();

    }
}
