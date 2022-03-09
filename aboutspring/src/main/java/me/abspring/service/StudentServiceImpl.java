package me.abspring.service;

import me.abspring.dao.StudentDao;
import me.abspring.entity.AnOtherTb;
import me.abspring.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
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

    private PlatformTransactionManager transactionManager;

    @Autowired
    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    /**
     * 使用全注解方式
     *
     * @param student
     * @return
     */
    @Override
    @Transactional
    public int addStudent(Student student) {
        AnOtherTb anOtherTb = new AnOtherTb();
        anOtherTb.setName(student.getName());
        anOtherTb.setEmail("xx@test.com");
        // 获取事务
        // 1. 使用  TransactionTemplates
        /*TransactionTemplate transactionTemplate = new TransactionTemplate();
        transactionTemplate.setTransactionManager(transactionManager);
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
            return null;
        });*/
        // 2. 使用Platform Transaction Manager
        // 定义事务属性
        /*DefaultTransactionDefinition transactionDef = new DefaultTransactionDefinition();
        // 设置传播行为
        transactionDef.setPropagationBehavior(DefaultTransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(transactionDef);
        try {
            int sNum = studentDao.insertStudent(student);
            int i = 1 / 0;
            int tnum = anOtherTbService.insertTestTb(anOtherTb);
            // 提交
            transactionManager.commit(status);
        } catch (Exception e) {
            // 回滚
            transactionManager.rollback(status);
        }*/
        try {
            for (int i = 0; i < 8; i++) {
                studentDao.insertStudent(student);
                anOtherTbService.insertTestTb(anOtherTb);
//                if (i == 3) {
//                    int temp = i / 0;
//                }
            }
        } catch (Exception e) {

        }

        return 1001;
    }
}
