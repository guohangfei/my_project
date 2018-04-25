package controller;

import com.github.pagehelper.util.StringUtil;
import entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.security.auth.Subject;

/**
 * @Author: 郭航飞
 * @Description: 登录相关操作
 * @Date: created in      2018/4/1215:01
 */
@Controller
public class LoginController {

    private Logger logger = LoggerFactory.getLogger("LoginController");

    @RequestMapping(value = "/login",method = { RequestMethod.GET, RequestMethod.POST })
    public String login(User user) {
        org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new
                UsernamePasswordToken(user.getUserName(),user.getPassword());
        try {
            subject.login(usernamePasswordToken);
            logger.info("======登陆成功=======");
            return "index";
        } catch (Exception e) {
            logger.error("======登陆异常=======");
            return "login";
        }
    }

}
