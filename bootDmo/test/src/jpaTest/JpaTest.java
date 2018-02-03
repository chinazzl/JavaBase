package src.jpaTest;

import oracle.sql.BLOB;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.my.booter.Runner;
import org.my.booter.dao.StuRepostiry;
import org.my.booter.vo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Felix on 2017/12/20.
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Runner.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class JpaTest {

    @Autowired
    private StuRepostiry stuRepostiry;
    @Autowired
    private EntityManager entityManager;


//    @Test
//    public void SelectStu(){
//        try{
//            List<Student> resultList = stuRepostiry.findAll();
//            for (int i = 0; i < resultList.size(); i++) {
//                System.out.println(resultList.get(i).getStuAge());
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            System.out.println("哟问题");
//        }
//
//    }
//
//    @Test
//    public void findStuById(){
//       String sql = "select sid,sname,sage,email,time,endtime,ssex from stu where 1=1";
//        Query query = entityManager.createNativeQuery(sql, Student.class);
//        List<Student> resultList = query.getResultList();
//        for (int i = 0; i < resultList.size(); i++) {
//            System.out.println("学生：" + resultList.get(i).getStuName());
//        }
//
//    }
//
//    @Test
//    public void findStuByIds(){
//        List<Object[]> list = stuRepostiry.findStudentById(1);
//        System.out.println(list.get(0));
//    }


    /**
     * 测试图片上传
     */
    @Test

    public void upLoad(){
        try {


            FileInputStream fis = new FileInputStream("C:\\Users\\lenovo\\Desktop\\MOMP.JPG");
            byte[] bytes = new byte[fis.available()];
            Student student = new Student();
            fis.read(bytes);
            fis.close();
            student.setImage(bytes);
            student.setSid("2");
            entityManager.persist(student);



        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void upInsert(){
        try{
            FileInputStream fis = new FileInputStream("C:\\Users\\lenovo\\Desktop\\MOMP.JPG");
            byte[] bytes = new byte[fis.available()];
            String sql = "insert into stu values(?,?)";
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter(1,"1");
            query.setParameter(2,bytes);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
