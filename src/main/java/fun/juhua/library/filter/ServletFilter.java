package fun.juhua.library.filter;
/**
 * @Description: 配置拦截器，未登录用户不可访问公共servlet
 * @Author: 2020031001
 * @Date: 15:03
 */

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringReader;

@WebFilter(filterName = "ServletFilter", urlPatterns = "/*")
public class ServletFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String role = (String) request.getSession().getAttribute("role");
        String requestURI = request.getRequestURI();
        //System.out.println("ServletFilter -> doFilter(15): " + requestURI);
        //未登录不能访问/UpdateReaderServlet读者信息编辑，/toEditReaderServlet跳转读者信息编辑页，/LogoutServlet登出操作
        if ((role == null || role == "")) {
            if (requestURI.equals("/UpdateReaderServlet") || requestURI.equals("/toEditReaderServlet") || requestURI.equals("/LogoutServlet")) {
                //重定向
                response.sendRedirect("/index.jsp");
                return;
            }
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
