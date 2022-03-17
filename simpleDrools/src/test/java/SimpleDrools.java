import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.definition.KiePackage;
import org.kie.api.definition.rule.Rule;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.testdrools.entity.ComparationOperation;
import org.testdrools.entity.Order;

import java.util.ArrayList;
import java.util.List;

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
        KieBase kieBase = kieClasspathContainer.getKieBase();
        for (KiePackage kiePackage : kieBase.getKiePackages()) {
            for (Rule rule : kiePackage.getRules()) {
                System.out.println("kp " + kiePackage + ": rule：" + rule.getName());
            }
        }
        KieSession kieSession = kieBase.newKieSession();
        // 创建会话 用于和规则引擎交互
//        KieSession kieSession = kieClasspathContainer.newKieSession("ksession-rule");
        Order order = new Order(210d,0d);
        // 将数据 存入 工作内存
        kieSession.insert(order);
        // 激活规则 引擎 ，扫描所有的规则
        int i = kieSession.fireAllRules();
        System.out.println("扫描规则共 " + i + "个");
        kieSession.dispose();
        System.out.println("优惠后的价格 " + order.getRealPrice());
    }

    @Test
    public void  testCompareOper() {
        KieServices kieServices = KieServices.Factory.get();
        KieSession kieSession = kieServices.getKieClasspathContainer().newKieSession();
        ComparationOperation comparationOperation = new ComparationOperation();
        comparationOperation.setUserName("sb");
        List<String> data = new ArrayList<>();
        data.add("zs");
        data.add("ls");
        comparationOperation.setData(data);
        kieSession.insert(comparationOperation);
        int i = kieSession.fireAllRules();
        System.out.println("扫描规则共 " + i + "个");
        kieSession.dispose();
    }
}
