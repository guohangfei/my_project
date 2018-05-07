package controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.CmsUtilService;
import util.PageInfo;

import javax.servlet.http.HttpServletRequest;

/**
 *页面临时显示跳转   之后根据情况更改
 *
 * @Author:          郭航飞
 * @CreateDate:   2018/5/7 16:59
**/
@Controller
public class ShowPageController {

    /**
     *表单显示页面
     *
     * @Author:          郭航飞
     * @CreateDate:   2018/5/7 17:01
    **/
    @RequestMapping(value = "/form_component")
    public String form_component(){
       return "form_component";
    }

    @RequestMapping(value = "/form_validation")
    public String form_validation(){
        return "form_validation";
    }

    /**
     *UI显示页面
     *
     * @Author:          郭航飞
     * @CreateDate:   2018/5/7 17:01
     **/
    @RequestMapping(value = "/general")
    public String general(){
        return "general";
    }
    @RequestMapping(value = "/buttons")
    public String buttons(){
        return "buttons";
    }
    @RequestMapping(value = "/grids")
    public String grids(){
        return "grids";
    }

    /**
     *小部件
     *
     * @Author:          郭航飞
     * @CreateDate:   2018/5/7 17:03
    **/
    @RequestMapping(value = "/widgets")
    public String widgets(){
        return "widgets";
    }

    /**
     *图表
     *
     * @Author:          郭航飞
     * @CreateDate:   2018/5/7 17:04
    **/
    @RequestMapping(value = "/chart-chartjs")
    public String chartChartjs(){
        return "chart-chartjs";
    }

    /**
     *表格
     *
     * @Author:          郭航飞
     * @CreateDate:   2018/5/7 17:05
    **/
    @RequestMapping(value = "/basic_table")
    public String basic_table(){
        return "basic_table";
    }

    /**
     *页面
     *
     * @Author:          郭航飞
     * @CreateDate:   2018/5/7 17:06
    **/
    @RequestMapping(value = "/profile")
    public String profile(){
        return "profile";
    }
    public String blank(){
        return "blank";
    }

}
