package com.shirorealm.dao.impl;

import com.shirorealm.dao.PermissionDao;
import com.shirorealm.entity.Permission;
import com.shirorealm.service.PermissionService;
import com.shirorealm.utils.JdbcTemplateUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.shirorealm.dao.impl
 * @Description:
 * @Date: 2021/4/27 19:00
 */
public class PermissionDaoImpl implements PermissionDao {

    private final JdbcTemplate jdbcTemplate = JdbcTemplateUtils.getJdbcTemplate();

    public Permission createPermission(final Permission permission) {
        final String sql = "insert into sys_permissions(permission, description, available) values(?,?,?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {

            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement psst = connection.prepareStatement(sql,  new String[] { "id" });
                psst.setString(1, permission.getPermission());
                psst.setString(2, permission.getDescription());
                psst.setBoolean(3, permission.getAvailable());
                return psst;
            }
        }, keyHolder);
        permission.setId(keyHolder.getKey().longValue());

        return permission;
    }

    public void deletePermission(Long permissionId) {
        //首先把与permission关联的相关表的数据删掉
        String sql = "delete from sys_roles_permissions where permission_id=?";
        jdbcTemplate.update(sql, permissionId);

        sql = "delete from sys_permissions where id=?";
        jdbcTemplate.update(sql, permissionId);
    }
}
