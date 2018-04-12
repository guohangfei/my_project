package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author: 郭航飞
 * @Description: java类作用描述
 * @Date: created in      2018/4/1211:42
 */
public class BasePathFilter implements Filter {


    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse rep, FilterChain fic) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        String prjPath = request.getScheme() + "://" + request.getServerName() + (request.getServerPort() == 80 ? "" : ":" + request.getServerPort()) + request.getContextPath();
        request.setAttribute("basePath", prjPath+"/");
        fic.doFilter(req, rep);
    }

    public void destroy() {

    }
}
