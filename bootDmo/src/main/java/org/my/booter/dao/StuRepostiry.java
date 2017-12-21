package org.my.booter.dao;

import org.my.booter.vo.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Felix on 2017/12/20.
 */
@Service
public interface StuRepostiry extends JpaRepository<Student,Integer> {

}
