import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.testdrools.entity.Student;

/**********************************
 * @author zhang zhao lin
 * @date 2022年03月21日 21:57
 * @Description:
 **********************************/
public class AdvanceSyntaxTest {

    /**
     * query
     */
    @Test
    public void queryTest() {
        KieContainer container = KieServices.get().getKieClasspathContainer();
        KieBase kieInnerField = container.getKieBase("kieInnerField");
        KieSession kieSession = kieInnerField.newKieSession();
        Student student1 = new Student();
        student1.setAge(50);
        student1.setName("zs");
        Student student2 = new Student();
        student2.setAge(50);
        student2.setName("ls");
        kieSession.insert(student1);
        kieSession.insert(student2);
        // 参数是定义的query的名字
        QueryResults queryResults = kieSession.getQueryResults("getStudentByAge");
        int size = queryResults.size();
        System.out.println("匹配到getStudentByAge 的查询规则有 " + size + "个");
        for (QueryResultsRow result : queryResults) {
            // 参数是实则知LHS的实体别名，当前的别名是$s @link{advancedQuery.drl}
            Student student = (Student) result.get("$s");
            System.out.println("匹配到无参的query Fact " + student);
        }

        QueryResults queryResults1 = kieSession.getQueryResults("getStudentByAgeAndName", "zs");
        for (QueryResultsRow result : queryResults1) {
            // 参数是实则知LHS的实体别名，当前的别名是$s @link{advancedQuery.drl}
            Student student = (Student) result.get("$s");
            System.out.println("匹配到有参的query Fact " + student);
        }
    }

    /**
     * function
     */
    @Test
    public void advancedFunction() {
        KieContainer container = KieServices.get().getKieClasspathContainer();
        KieBase kieInnerField = container.getKieBase("kieInnerField");
        KieSession kieSession = kieInnerField.newKieSession();
        Student student1 = new Student();
        student1.setAge(61);
        student1.setName("zs");
        kieSession.insert(student1);
        int i = kieSession.fireAllRules();
        System.out.println(i);
        kieSession.dispose();
    }
}
