package shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import java.util.concurrent.atomic.AtomicInteger;

public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    private Cache<String, AtomicInteger> passwordRetryCache;

    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        // TODO Auto-generated method stub
        String principal = (String) token.getPrincipal();
        AtomicInteger atomicInteger = passwordRetryCache.get(principal);

        if (atomicInteger == null) {
            atomicInteger = new AtomicInteger(0);
            passwordRetryCache.put(principal, atomicInteger);
        }

        // 如果重试次数超过5次则抛出异常
        if (atomicInteger.incrementAndGet() > 5) {
            throw new ExcessiveAttemptsException();
        }

        // 调用父类的验证
        boolean doCredentialsMatch = super.doCredentialsMatch(token, info);

        // 如果账号密码正确，清除重试次数
        if (doCredentialsMatch) {
            passwordRetryCache.remove(principal);
        }

        return doCredentialsMatch;

    }

}
