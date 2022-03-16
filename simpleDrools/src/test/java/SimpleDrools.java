import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.Agenda;
import org.testdrools.entity.Order;

/**
 * @author Julyan
 * @version V1.0
 * @Description:
 * @Date: 2022/3/16 14:38
 */
public class SimpleDrools {

    @Test
    public void firstDrools() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        // 创建会话 用于和规则引擎交互
        KieSession kieSession = kieClasspathContainer.newKieSession("ksession-rule");
        Agenda agenda = kieSession.getAgenda();
        System.out.println("agenda "+agenda);
        Order order = new Order();
        order.setOriginalPrice(210d);
        // 将数据 存入 工作内存
        kieSession.insert(order);
        // 激活规则 引擎 ，扫描所有的规则
        int i = kieSession.fireAllRules();
        System.out.println("扫描规则共 " + i + "个");
        kieSession.dispose();
        System.out.println("优惠后的价格 " + order.getRealPrice());

    }
}
