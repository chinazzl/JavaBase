package com.shirorealm.credentitals;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.shirorealm.credentitals
 * @Description:
 * @Date: 2021/4/27 14:24
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {
    private Ehcache passwordRetryCache;

    public RetryLimitHashedCredentialsMatcher() {
        CacheManager cacheManager = CacheManager.newInstance(CacheManager.class.getClassLoader().
                getResource("ehcache.xml"));
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }

    /**
     * AuthenticationToken 用于收集用户提交的身份（如用户名）及凭据（如密码）：
     * AuthenticationInfo 有两个作用：
     * <p>
     * 如果 Realm 是 AuthenticatingRealm 子类，则提供给 AuthenticatingRealm 内部使用的 CredentialsMatcher 进行凭据验证；（如果没有继承它需要在自己的 Realm 中自己实现验证）；
     * 提供给 SecurityManager 来创建 Subject（提供身份信息）；
     * <p>
     * 比如 HashedCredentialsMatcher，在验证时会判断 AuthenticationInfo 是否是 SaltedAuthenticationInfo 子类，来获取盐信息。
     * <p>
     * Account 相当于我们之前的 User，SimpleAccount 是其一个实现；在 IniRealm、PropertiesRealm 这种静态创建帐号信息的场景中使用，
     * 这些 Realm 直接继承了 SimpleAccountRealm，而 SimpleAccountRealm 提供了相关的 API 来动态维护 SimpleAccount；
     * 即可以通过这些 API 来动态增删改查 SimpleAccount；动态增删改查角色 / 权限信息。
     * 及如果您的帐号不是特别多，可以使用这种方式，具体请参考 SimpleAccountRealm Javadoc。
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String) token.getPrincipal();
        Element element = passwordRetryCache.get(username);
        if (element == null) {
            element = new Element(username,new AtomicInteger(0));
            passwordRetryCache.put(element);
        }
        AtomicInteger retryCount = (AtomicInteger)element.getObjectValue();
        if (retryCount.incrementAndGet() > 5) {
            throw new ExcessiveAttemptsException();
        }
        boolean matchers = super.doCredentialsMatch(token, info);
        if (matchers) {
            //如果匹配了则 直接将 缓存中的信息删除，相当于重试次数重新计算
            passwordRetryCache.remove(username);
        }
        return matchers;
    }
}
