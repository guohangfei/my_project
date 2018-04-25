package controller;

import com.github.pagehelper.util.StringUtil;
import entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: 郭航飞
 * @Description: 登录相关操作
 * @Date: created in      2018/4/1215:01
 */
@Controller
public class LoginController {

    private Logger logger = LoggerFactory.getLogger("LoginController");

    @Autowired
    private UserController userController;

    /**
     * @Author:          郭航飞
     * @Description:： 用户登录判断
     * @CreateDate:   2018/4/25 17:34
     * @param         user
     * @return         对应的视图层
    **/
    @RequestMapping(value = "/login",method = { RequestMethod.GET, RequestMethod.POST })
    public String login(User user,Model model) {
        // 如果已经登陆，无需重新登录
        if (isRelogin(user)){
            return  "index";
        }

        //用户为空时，直接跳转登录页面：解决启动为空登录报错
        if (StringUtil.isEmpty(user.getUserName())){
            return  "login";
        }

        //用户名称不存在，直接跳转登录页面
        User resUser = userController.queryUserByName(user.getUserName());
        if (StringUtil.isEmpty(resUser.getUserName())){
            return  "login";
        }

        // 组装token，包括用户名、密码
        UsernamePasswordToken usernamePasswordToken = new
                UsernamePasswordToken(user.getUserName(),user.getPassword());

        // shiro登陆验证
        try {
            SecurityUtils.getSubject().login(usernamePasswordToken);
        } catch (UnknownAccountException ex) {
            model.addAttribute("failMsg", "用户不存在或密码错误！");
            return "login";
        } catch (IncorrectCredentialsException ex) {
            model.addAttribute("failMsg", "用户不存在或者密码错误！");
            return "login";
        } catch (AuthenticationException ex) {
            model.addAttribute("failMsg", "未知错误，请重试！");
            return"login";
        } catch (Exception ex) {
            model.addAttribute("failMsg", "未知错误，请重试！");
            return"login";
        }
        return "index";
    }

    /**
     * @Author:          郭航飞
     * @Description:： 登出操作
     * @CreateDate:   2018/4/25 17:45
    **/
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            try{
                subject.logout();
            }catch(Exception ex){
            }
        }
        return "login";
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
