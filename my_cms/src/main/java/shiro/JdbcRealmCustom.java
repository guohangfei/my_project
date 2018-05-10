package shiro;

import com.github.pagehelper.util.StringUtil;
import controller.UserController;
import entity.*;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCache;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import result.KeyValudBean;
import service.UserService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: 郭航飞
 * @Description: java类作用描述
 * @Date: created in      2018/4/1215:40
 */
public class JdbcRealmCustom extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger("JdbcRealmCustom");

    @Autowired
    private UserService userService;
    @Autowired
    private EhCacheManager ehCacheManager;

    private static String userName;
    private User user;

    /**
     * 用户授权认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 从数据库中获取当前登录用户的详细信息
        if (user!=null){
//            根据用户名称获得用户角色  一个用户可以拥有多个角色
            List<KeyValudBean> userRoles=userService.getRoleByUserId(user.getUserId());
            Set<String> rightList= new HashSet<String>();;
            Set<String> roleList = new HashSet<String>();;
            List<KeyValudBean> rightNames ;
            for (KeyValudBean userRole:userRoles) {
                roleList.add(userRole.getValue());
                //查询对应角色的对应权限集合
                rightNames=userService.getRightByRoleId(userRole.getKey());
                for (KeyValudBean rightName : rightNames) {
                    if(StringUtils.isNotBlank(rightName.getValue())){
                        rightList.add(rightName.getValue());
                    }
                }
            }

            //赋角色
            info.addRoles(roleList);
            //赋权限
            info.addStringPermissions(rightList);
            return info;
        }
        return null;
    }

    /**
     * 用户登陆认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 如果已经登陆，无需重新登录
        //首先从缓存中获取记录
        EhCache cache = (EhCache) ehCacheManager.getCache("userNameAndPass");
        logger.info("======用户登陆认证======");
        //UsernamePasswordToken对象用来存放提交的登录信息
        userName = authenticationToken.getPrincipal().toString();
        String password;
        if (StringUtil.isEmpty((String)cache.get(userName))) {
            user=userService.queryUserByName(userName);
            password=user.getPassword();
        }else{
            password=(String)cache.get(userName);
            System.err.printf("**********************去缓存获得密码******************************");
        }
        //查出是否有此用户
//      缓存用户名和密码
        cache.put(userName,password);
        if(user!=null || StringUtil.isNotEmpty(password)){
            //若存在，将此用户存放到登录认证info中
            return new SimpleAuthenticationInfo(userName, password, getName());
        }
        return null;
    }



    /**
     * @Author:          郭航飞
     * @Description:：已经登录处理
     * @CreateDate:   2018/4/25 17:55
     **/
    private boolean isRelogin(User user) {
        Subject us = SecurityUtils.getSubject();
        if (us.isAuthenticated()) {
            // 参数未改变，无需重新登录，默认为已经登录成功
            return true;
        }
        // 需要重新登陆
        return false;
    }

}
