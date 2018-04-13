package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: 郭航飞
 * @Description: 登录相关操作
 * @Date: created in      2018/4/1215:01
 */
@Controller
public class LoginController {

    private Logger log = LoggerFactory.getLogger("LoginController");

    @RequestMapping(value = "/login",method = { RequestMethod.GET, RequestMethod.POST })
    public String login() {
        
        return "login";
    }

}
