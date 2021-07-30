package com.shirorealm.dao;

import com.shirorealm.entity.Permission;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.shirorealm.dao
 * @Description:
 * @Date: 2021/4/27 18:52
 */
public interface PermissionDao {
    public Permission createPermission(Permission permission);

    public void deletePermission(Long permissionId);

}
