package com.shirorealm.service;

import com.shirorealm.entity.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.shirorealm.service
 * @Description:
 * @Date: 2021/4/27 10:34
 */
public class PasswordHelper {
    private final RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    private final String algorithmName = "md5";
    private final int hashIterations = 2;

    public void encryPassword(User user) {
        user.setSalt(randomNumberGenerator.nextBytes().toHex());
        String newPassword = new SimpleHash(algorithmName, user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()), hashIterations).toHex();
        user.setPassword(newPassword);
    }


}
