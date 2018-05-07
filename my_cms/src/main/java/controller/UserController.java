package controller;

import com.github.pagehelper.util.StringUtil;
import entity.User;
import entity.UserExample;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import javax.annotation.Resource;
import javax.xml.soap.SAAJResult;
import java.util.List;

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
     public User queryUserByName(String userName) {
         UserExample userExample=new UserExample();
         UserExample.Criteria criteria = userExample.createCriteria();
         if (StringUtil.isNotEmpty(userName)){
             criteria.andUserNameEqualTo(userName);
         }
         List<User> listUser = userService.selectByExample(userExample);
         User resultUser=listUser.isEmpty()?new User():listUser.get(0);
         return  resultUser;
     }

}
