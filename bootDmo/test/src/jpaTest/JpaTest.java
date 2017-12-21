package src.jpaTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.my.booter.Runner;
import org.my.booter.dao.StuRepostiry;
import org.my.booter.vo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Felix on 2017/12/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Runner.class)
public class JpaTest {

    @Autowired
    private StuRepostiry stuRepostiry;
    @Autowired
    private EntityManager entityManager;

    @Test
    public void SelectStu(){
        try{
            List<Student> resultList = stuRepostiry.findAll();
            for (int i = 0; i < resultList.size(); i++) {
                System.out.println(resultList.get(i).getStuAge());
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("哟问题");
        }

    }

    @Test
    public void findStuById(){
       String sql = "select sid,sname,sage,email,time,endtime,ssex from stu where 1=1";
        Query query = entityManager.createNativeQuery(sql, Student.class);
        List<Student> resultList = query.getResultList();
        for (int i = 0; i < resultList.size(); i++) {
            System.out.println("学生：" + resultList.get(i).getStuName());
        }

    }
}
