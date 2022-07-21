import com.testdemo.studentproject.Student;
import org.drools.core.RuleBaseConfiguration;
import org.drools.decisiontable.InputType;
import org.drools.decisiontable.SpreadsheetCompiler;
import org.drools.template.ObjectDataCompiler;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;

import java.io.File;
import java.io.FileInputStream;

/**
 * @author Julyan
 * @version V1.0
 * @Title: JavaBase
 * @Package PACKAGE_NAME
 * @Description: 使用决策表通过指定得excel表格进行生成drl语句。相当于模板。
 * @Date: 2022/7/19 16:55
 */
public class DecisionTableDrools {

    private static final String DECISION_TABLE_XLSX = "D:\\IdeaProjects\\JavaBase\\simpleDrools\\src\\main\\resources\\META-INF\\simple_decision_table.xlsx";
    private static final String DECISION_TABLE_XLSX_CLASS_PATH = "META-INF/simple_decision_table.xlsx";
    private static final String TEMPLATE_TABLE_XLSX_CLASS_PATH = "META-INF/simple_template.xlsx";

    /**
     * 使用file流进行读取drl
     */
    @Test
    public void testSimpleDecisionTables() {
        File file = new File(DECISION_TABLE_XLSX);
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            SpreadsheetCompiler converter = new SpreadsheetCompiler();
            String drl = converter.compile(fileInputStream, InputType.XLS);
            System.out.println(drl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用java api进行读取
     */
    @Test
    public void testXlsApi() {
        Resource dis = ResourceFactory.newClassPathResource(DECISION_TABLE_XLSX_CLASS_PATH);
        ObjectDataCompiler converter = new ObjectDataCompiler();
        KieHelper helper = new KieHelper();
        helper.addResource(dis, ResourceType.DTABLE);
        KieSession kieSession = helper.build().newKieSession();
        int i = kieSession.fireAllRules();
        System.out.println(" " + i);
        kieSession.dispose();
    }

    /**
     * 自然语言转换规则语句
     */
    @Test
    public void testDSLKeyword() {
        Resource dsl = ResourceFactory.newClassPathResource("dsl_rules/StudentDSL.dsl");
        Resource dslr = ResourceFactory.newClassPathResource("dsl_rules/StudentDslr.dslr");
        KieHelper helper = new KieHelper();
        helper.addResource(dsl, ResourceType.DSL);
        helper.addResource(dslr, ResourceType.DSLR);
        KieSession kieSession = helper.build().newKieSession();
        Student student = new Student();
        student.setAge(50);
        student.setName("张三");
        student.setPost("班长");
        kieSession.insert(student);
        int i = kieSession.fireAllRules();
        System.out.println(student.getPost() + student.getName() + student.getAge());
        kieSession.dispose();
    }
}
