package me.abspring.test;

import me.abspring.entity.Student;
import me.abspring.service.StudentService;
import org.kie.api.KieBase;
import org.kie.api.cdi.KBase;
import org.kie.api.runtime.KieSession;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author Julyan
 * @version V1.0
 * @Description:
 * @Date: 2022/3/8 10:33
 */
public class TxSpringTest {

    private static StudentService studentService;

    @KBase("kbase")
    private  KieBase kieBase;


    public static void main(String[] args) {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/application.xml");
//        studentService = (StudentService) context.getBean("studentService");
//        Student student = new Student();
//        student.setName("zs");
//        student.setAge(12);
//        studentService.addStudent(student);
//        KieSession kieSession = kieBase.newKieSession();
//        int i = kieSession.fireAllRules();
//        System.out.println(i);
//        kieSession.dispose();
    }
}
