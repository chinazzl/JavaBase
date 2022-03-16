import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.MessageFormat;

/**
 * @author Julyan
 * @version V1.0
 * @Description:
 * @Date: 2022/3/8 10:21
 */
public class TxSpringTest {

    @Test
    public void testt() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/spring/application.xml");
        String st = "经由约翰霍普金斯大学{0}，{1}";
        String second = String.format(st, "（第一个）", "second");
        System.out.println(second);

        String format = MessageFormat.format(st, "first", "second");
        System.out.println(format);
    }


}
