package fun.juhua.library.filter;
/**
 * @Description: 配置拦截器，拦截非管理员用户，并重定向对应的index.jsp
 * @Author: 2020031001
 * @Date: 15:02
 */

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "ReaderFilter", urlPatterns = "/reader/*")
public class ReaderFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        String role = (String) ((HttpServletRequest) req).getSession().getAttribute("role");
        //System.out.println("UserFilter -> doFilter(21): " + role);
        HttpServletResponse response = (HttpServletResponse) resp;
        if (role == null || role == "") {
            //未登录的，重定向首页
            response.sendRedirect("/index.jsp");
            return;
        }
        if ("admin".equals(role)) {
            //重定向
            response.sendRedirect("/admin/index.jsp");
            return;
        }
        //放行
        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
