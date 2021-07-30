package com.shirorealm.service.impl;

import com.shirorealm.dao.RoleDao;
import com.shirorealm.dao.impl.RoleDaoImpl;
import com.shirorealm.entity.Role;
import com.shirorealm.service.RoleService;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.shirorealm.service.impl
 * @Description:
 * @Date: 2021/4/27 19:12
 */
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao = new RoleDaoImpl();


    public Role createRole(Role role) {
        return roleDao.createRole(role);
    }

    public void deleteRole(Long roleId) {
        roleDao.deleteRole(roleId);
    }

    public void correlationPermissions(Long roleId, Long... permissionIds) {
        roleDao.correlationPermissions(roleId,permissionIds);
    }

    public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
        roleDao.uncorrelationPermissions(roleId,permissionIds);
    }
}
