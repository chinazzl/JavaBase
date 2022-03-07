package me.abspring.service;

import me.abspring.dao.StudentDao;
import me.abspring.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**********************************
 * @author zhang zhao lin
 * @date 2022年03月07日 22:47
 * @Description:
 **********************************/
@Service("studentService")
public class StudentServiceImpl implements StrudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public int addStudent(Student student) {
        // 获取事务
        return studentDao.insertStudent(student);
    }
}
