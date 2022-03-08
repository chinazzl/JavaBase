package me.abspring.service;

import me.abspring.dao.StudentDao;
import me.abspring.entity.AnOtherTb;
import me.abspring.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

/**********************************
 * @author zhang zhao lin
 * @date 2022年03月07日 22:47
 * @Description:
 **********************************/
@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private AnOtherTbService anOtherTbService;

   @Autowired
   private TransactionTemplate transactionTemplate;

    @Override
    public int addStudent(Student student) {
        AnOtherTb anOtherTb = new AnOtherTb();
        anOtherTb.setName(student.getName());
        anOtherTb.setEmail("xx@test.com");
        // 获取事务
        // 1. 使用  TransactionTemplates
        transactionTemplate.execute((status) -> {
            int sNum = 0, tnum = 0;
            try {
                sNum = studentDao.insertStudent(student);
                int i = 1 / 0;
                tnum = anOtherTbService.insertTestTb(anOtherTb);
                return sNum + tnum;
            } catch (Exception e) {
                status.setRollbackOnly();
            }
            return sNum + tnum;
        });
        return 1001;
    }
}
