package com.shirorealm.dao;

import com.shirorealm.entity.Role;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.shirorealm.dao
 * @Description:
 * @Date: 2021/4/27 19:13
 */
public interface RoleDao {
    public Role createRole(Role role);

    public void deleteRole(Long roleId);

    public void correlationPermissions(Long roleId, Long... permissionIds);

    public void uncorrelationPermissions(Long roleId, Long... permissionIds);

}
