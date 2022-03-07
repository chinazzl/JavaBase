package me.abspring.dao;

import me.abspring.entity.Student;

import java.util.List;

/**********************************
 * @author zhang zhao lin
 * @date 2022年03月07日 22:22
 * @Description:
 **********************************/
public interface StudentDao {

    int insertStudent(Student student);

    List<Student> getAllStudent();

    Student getStudentById(int id);
}
