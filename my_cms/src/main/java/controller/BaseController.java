package controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.CmsUtilService;
import util.PageInfo;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BaseController {
    public static final String OFFSET_KEY = "offset";
    public static final String LIMIT_KEY = "limit";

    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected CmsUtilService cmsUtilService;


    protected PageInfo getPageInfo(){
        String offset = request.getParameter(OFFSET_KEY);
        String limit = request.getParameter(LIMIT_KEY);

        if(null != offset && null != limit
                && StringUtils.isNumeric(offset) && StringUtils.isNumeric(limit)){
            Integer iOffset = Integer.valueOf(offset);
            Integer iLimit = Integer.valueOf(limit);
            return new PageInfo((iOffset + iLimit) / iLimit, iLimit);
        }

        return null;
    }
    protected void processOrder(Object example, Class<?> mapperedClass){
        String sort = request.getParameter("sort");
        String order = request.getParameter("order");

        if(!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)){
            cmsUtilService.processOrder(example, mapperedClass, sort,
                    "asc".equalsIgnoreCase(order) ? CmsUtilService.ORDER.ASC : CmsUtilService.ORDER.DESC);
        }
    }
}
