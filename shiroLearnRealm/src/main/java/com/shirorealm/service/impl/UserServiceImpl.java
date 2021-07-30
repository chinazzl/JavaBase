package com.shirorealm.service.impl;

import com.shirorealm.dao.UserDao;
import com.shirorealm.dao.impl.UserDaoImpl;
import com.shirorealm.entity.User;
import com.shirorealm.service.PasswordHelper;
import com.shirorealm.service.UserService;

import java.util.Set;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.shirorealm.service.impl
 * @Description:
 * @Date: 2021/4/27 10:59
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    private PasswordHelper passwordHelper = new PasswordHelper();

    public User createUser(User user) {
        passwordHelper.encryPassword(user);
        return userDao.createUser(user);
    }

    public void changePassword(Long userId, String newPassword) {

    }

    public void correlationRoles(Long userId, Long... roleIds) {

    }

    public void uncorrelationRoles(Long userId, Long... roleIds) {

    }

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public Set<String> findRoles(String username) {
        return userDao.findRoles(username);
    }

    public Set<String> findPermissions(String username) {
        return userDao.findPermissions(username);
    }
}
