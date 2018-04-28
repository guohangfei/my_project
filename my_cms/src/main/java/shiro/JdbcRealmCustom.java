package shiro;

import controller.UserController;
import entity.Right;
import entity.Role;
import entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import service.UserService;

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
    private UserController userController;
    @Autowired
    private UserService userService;

    /**
     * 用户授权认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取当前登录的用户名
        String username = (String) super.getAvailablePrincipal(principalCollection);
        // 从数据库中获取当前登录用户的详细信息
        User user=userController.queryUserByName(username);
        if (user!=null){
//            根据用户名称活动用户角色  此项目一个用户只允许拥有一个权限最高角色
            Role role=userService.getRoleByUserName(user.getUserName());
            List<Right> listRight=userService.getRightByRoleId(role.getRoleId());
            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
            SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
            info.addRole(role.getRoleName());
            for (Right right: listRight) {
                info.addStringPermission(right.getRightName());
            }
            return info;
        }
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

    /**
     * 将一些数据放到ShiroSession中,以便于其它地方使用
     *
     * 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
     */
    private void setSession(Object key, Object value) {
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
            Session session = currentUser.getSession();
            System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
            if (null != session) {
                session.setAttribute(key, value);
            }
        }
    }
}
