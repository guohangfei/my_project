package shiro;

import controller.UserController;
import entity.Role;
import entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: 郭航飞
 * @Description: java类作用描述
 * @Date: created in      2018/4/1215:40
 */
public class JdbcRealmCustom extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger("JdbcRealmCustom");

    @Autowired
    private UserController userController;
    /**
     * 用户授权认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 用户登陆认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        logger.info("======用户登陆认证======");
        //UsernamePasswordToken对象用来存放提交的登录信息
        String userName = authenticationToken.getPrincipal().toString();
        //查出是否有此用户
        User user=userController.queryUserByName(userName);
        if(user!=null){
            //若存在，将此用户存放到登录认证info中
            return new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), getName());
        }
        return null;
    }
}
