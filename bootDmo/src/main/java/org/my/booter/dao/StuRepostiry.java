package org.my.booter.dao;

import org.my.booter.vo.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Felix on 2017/12/20.
 */
@Repository
public interface StuRepostiry extends JpaRepository<Student,Integer> {

    @Query(value = "select sname from stu where sid = ?1",nativeQuery = true)
    List<Object[]> findStudentById(int id);

    @Transactional
    @Modifying
    @Query(value = "insert into stu(sid,imageurl) VALUES (SEQ_STU.NEXTVAL,?1)",nativeQuery = true)
    void uploadImage(String originalFilename);
}
