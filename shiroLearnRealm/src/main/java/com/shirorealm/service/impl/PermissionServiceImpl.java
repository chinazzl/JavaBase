package com.shirorealm.service.impl;

import com.shirorealm.dao.PermissionDao;
import com.shirorealm.dao.impl.PermissionDaoImpl;
import com.shirorealm.entity.Permission;
import com.shirorealm.service.PermissionService;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.shirorealm.service.impl
 * @Description:
 * @Date: 2021/4/27 18:52
 */
public class PermissionServiceImpl implements PermissionService {

    private PermissionDao permissionDao = new PermissionDaoImpl();

    public Permission createPermission(Permission permission) {
        return permissionDao.createPermission(permission);
    }

    public void deletePermission(Long permissionId) {
        permissionDao.deletePermission(permissionId);
    }
}
