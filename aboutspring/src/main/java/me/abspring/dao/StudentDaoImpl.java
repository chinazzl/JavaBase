package me.abspring.dao;

import me.abspring.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**********************************
 * @author zhang zhao lin
 * @date 2022年03月07日 22:24
 * @Description:
 **********************************/
@Repository
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insertStudent(Student student) {
        String sql = "INSERT INTO STU(NAME,AGE) VALUES(?,?)";
        return jdbcTemplate.update(sql,new Object[]{student.getName(),student.getAge()});
    }

    @Override
    public List<Student> getAllStudent() {
        return null;
    }

    @Override
    public Student getStudentById(int id) {
        return null;
    }
}
