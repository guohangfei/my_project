package controller;

import com.nasoft.log.aspect.SystemLog;
import com.nasoft.log.util.LogTab;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: 郭航飞
 * @Description: java类作用描述
 * @Date: created in      2018/4/1113:42
 */
@Controller
public class IndexController {

    private Logger log = LoggerFactory.getLogger("IndexController");
    @RequestMapping(value = "/index",method = { RequestMethod.GET, RequestMethod.POST })
    public String index() {
        System.out.println("进入首页");
        return "index";
    }
}
