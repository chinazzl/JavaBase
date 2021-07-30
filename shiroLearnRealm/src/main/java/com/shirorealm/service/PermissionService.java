package com.shirorealm.service;


import com.shirorealm.entity.Permission;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.shirorealm.service
 * @Description:
 * @Date: 2021/4/27 10:20
 */
public interface PermissionService {
    public Permission createPermission(Permission permission);
    public void deletePermission(Long permissionId);
}
