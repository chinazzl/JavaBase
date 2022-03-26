import com.testdemo.studentproject.Student;
import org.drools.core.io.impl.UrlResource;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Julyan
 * @version V1.0
 * @Description: 使用workbench => 创建repository -> 创建module -> 创建container -> 创建ksession -> 调用规则
 * 注意：创建的实体必须和workBench中定义的包路径相同。
 * @Date: 2022/3/26 12:56
 */
public class DroolsByWorkbench {

    // 通过drools workbench 构建发布的jar
    private final static String url = "http://localhost:7001/kie-drools-wb/maven2/com/testdemo/StudentProject/1.0.0/StudentProject-1.0.0.jar";

    @Test
    public void workBenchEngines() throws IOException {
        KieServices kieServices = KieServices.get();

        // 根据远程的url 创建  urlResource,并且设置登录信息
        UrlResource resource = (UrlResource) kieServices.getResources().newUrlResource(url);
        resource.setUsername("kie");
        resource.setPassword("kie");
        resource.setBasicAuthentication("enabled");
        // 根据url 通过IO 处理 读取相关jar。
        InputStream inputStream = resource.getInputStream();
        Resource newInputStreamResource = kieServices.getResources().newInputStreamResource(inputStream);
        // 获取 repository
        KieRepository repository = kieServices.getRepository();
        // 通过inputStream 创建 kieModule
        KieModule kieModule = repository.addKieModule(newInputStreamResource);
        KieContainer kieContainer = kieServices.newKieContainer(kieModule.getReleaseId());
        KieBase kieBase = kieContainer.getKieBase("k1");
        System.out.println("扫描到包 共 " + kieBase.getKiePackages().size() + "个");
        KieSession kieSession = kieBase.newKieSession();
        Student student = new Student();
        student.setName("zs");
        student.setAge(15);
        kieSession.insert(student);
        kieSession.fireAllRules();
        kieSession.dispose();
    }
}


