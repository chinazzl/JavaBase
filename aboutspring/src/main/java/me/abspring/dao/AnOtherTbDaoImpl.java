package me.abspring.dao;

import me.abspring.entity.AnOtherTb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Julyan
 * @version V1.0
 * @Description:
 * @Date: 2022/3/8 9:33
 */
@Repository
public class AnOtherTbDaoImpl implements AnOtherTbDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insertTestTb(AnOtherTb otherTb) {
        String sql = "INSERT INTO TEST_TABLE(NAME,EMAIL) VALUES(?,?)";
        return jdbcTemplate.update(sql, otherTb.getName(),otherTb.getEmail());
    }
}
