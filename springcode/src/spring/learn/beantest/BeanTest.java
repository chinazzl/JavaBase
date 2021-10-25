package spring.learn.beantest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package spring.learn.beantest
 * @Description:
 * @Date: 2021/10/9 9:56
 */
public class BeanTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Simple simple = (Simple) context.getBean("simple");
        simple.getUser();
    }
}
