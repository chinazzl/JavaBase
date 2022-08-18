import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieSession;

/**
 * @author Julyan
 * @version V1.0
 * @Title: JavaBase
 * @Package PACKAGE_NAME
 * @Description:
 * @Date: 2022/7/21 16:36
 */
public class TemplateDrools {

    @Test
    public void testTemplateDrt() {
        KieServices services = KieServices.get();
        KieSession templateSession = services.getKieClasspathContainer().newKieSession("templateSession");
        
        templateSession.fireAllRules();
        templateSession.dispose();
    }
}
