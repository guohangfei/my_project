package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.UserService;
import javax.annotation.Resource;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {

    @Resource
    protected UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String main() {
		return "user";
	}

	 /**
	  * @Author:         郭航飞
	  * @Description:    判断用户是否存在，
	  * @CreateDate:   2018/4/12 18:55
	  */
}
