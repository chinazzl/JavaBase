import org.drools.core.base.RuleNameEndsWithAgendaFilter;
import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.drools.core.base.RuleNameMatchesAgendaFilter;
import org.drools.core.base.RuleNameStartsWithAgendaFilter;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.definition.KiePackage;
import org.kie.api.definition.rule.Rule;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.testdrools.entity.ComparationOperation;
import org.testdrools.entity.Order;
import org.testdrools.entity.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Julyan
 * @version V1.0
 * @Description: Bean先加入到工作内存变成fact对象 => 和规则库加载 => 规则库和fact对象匹配 => 匹配成功的加入Agenda 议程 => 然后执行HRS
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

    /**
     * 使用比较语法
     */
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

    // 指定规则进行匹配
    @Test
    public void testAppointRules() {
        KieServices kieServices = KieServices.Factory.get();
        KieSession kieSession = kieServices.getKieClasspathContainer().newKieSession();
        ComparationOperation comparationOperation = new ComparationOperation();
        comparationOperation.setUserName("sb");
        List<String> data = new ArrayList<>();
        data.add("zs");
        data.add("ls");
        comparationOperation.setData(data);
        kieSession.insert(comparationOperation);
        // 指定具体的规则名字进行匹配
//        int i = kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("comparationMatched"));
        // 指定前缀、后缀、正则。
        // 后缀
        int i = kieSession.fireAllRules(new RuleNameEndsWithAgendaFilter("Matched"));
        // 前缀
//        kieSession.fireAllRules(new RuleNameStartsWithAgendaFilter("comparation"));
        // 正则
//        kieSession.fireAllRules(new RuleNameMatchesAgendaFilter("comparation.*"));

        System.out.println("扫描规则共 " + i + "个");
        kieSession.dispose();
    }

    // 内置方法 update
    @Test
    public void testUpdateRule() {
        KieServices kieServices = KieServices.Factory.get();
        KieSession kieSession = kieServices.getKieClasspathContainer().newKieSession();
        Student student = new Student();
        student.setAge(5);
        kieSession.insert(student);
        int i = kieSession.fireAllRules();
        System.out.println("扫描规则共 " + i + "个");

        student.setAge(15);
        kieSession.insert(student);
        int m= kieSession.fireAllRules();
        System.out.println("扫描规则共 " + m + "个");
        kieSession.dispose();
    }

    // 内置方法 insert
    @Test
    public void testInsert() {
        KieServices kieServices = KieServices.get();
        KieSession kieSession = kieServices.newKieClasspathContainer().newKieSession();
        Student student = new Student();
        student.setAge(30);
        kieSession.insert(student);
        kieSession.fireAllRules();
        kieSession.dispose();
    }

    @Test
    public void testActiveGroup() {
        KieContainer kieClasspathContainer = KieServices.get().newKieClasspathContainer();
        KieBase kieInnerField = kieClasspathContainer.getKieBase("kieInnerField");
        KieSession kieSession = kieInnerField.newKieSession();
        kieSession.fireAllRules();
        kieSession.dispose();
    }

    @Test
    public void testAgentGroup() {
        KieContainer kieClasspathContainer = KieServices.get().newKieClasspathContainer();
        KieBase kieInnerField = kieClasspathContainer.getKieBase("kieInnerField");
        KieSession kieSession = kieInnerField.newKieSession();
        kieSession.getAgenda().getAgendaGroup("myagendaGroup").setFocus();
        kieSession.fireAllRules();
        kieSession.dispose();
    }

}
