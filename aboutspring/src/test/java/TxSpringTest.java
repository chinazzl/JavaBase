import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieBase;
import org.kie.api.cdi.KBase;
import org.kie.api.runtime.KieSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.MessageFormat;

/**
 * @author Julyan
 * @version V1.0
 * @Description:
 * @Date: 2022/3/8 10:21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application.xml")
public class TxSpringTest {

    @Test
    public void testt() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/spring/application.xml");
        String st = "经由约翰霍普金斯大学{0}，{1}";
        String second = String.format(st, "（第一个）", "second");
        System.out.println(second);

        String format = MessageFormat.format(st, "first", "second");
        System.out.println(format);

        String s = "xxx%s";
        String aa = String.format(s, "aa");
        System.out.println(aa);

    }

    @KBase("kbase")
    private KieBase kieBase;
    @Test
    public void kieForSpring() {
        System.out.println(kieBase.getKiePackages().size());
        KieSession kieSession = kieBase.newKieSession();
        int i = kieSession.fireAllRules();
        System.out.println(i);
        kieSession.dispose();
    }


}
