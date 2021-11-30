package fun.juhua.library.filter;
/**
 * @Description: 拦截非管理员用户，并重定向对应的index.jsp
 * @Author: 2020031001
 * @Date: 11:17
 */

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AdminFilter", urlPatterns = "/admin/*")
public class AdminFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        String role = (String) ((HttpServletRequest) req).getSession().getAttribute("role");
        //System.out.println("AdminFilter -> doFilter(21): " + role);
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
        if (role == null || role == "") {
            //重定向
            httpServletResponse.sendRedirect("/index.jsp");
            return;
        }
        if (role == "reader") {
            //重定向
            httpServletResponse.sendRedirect("/reader/index.jsp");
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
