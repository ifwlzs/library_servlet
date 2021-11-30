package fun.juhua.library.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @program: library
 * @description: 配置拦截器
 * @author:
 * @create: 2021-10-26 09:04
 **/

/**
 * @Description: 对所有的web资源进行拦截，设置初始化编码
 * @Author: 2020031001
 * @Date: 09:05
 */
@WebFilter(filterName = "CharsetFilter",
        urlPatterns = "/*",
        initParams = {
                @WebInitParam(name = "charset", value = "utf-8")
        })
public class CharsetFilter implements Filter {
    private String filterName;
    private String charset;

    @Override
    public void destroy() {
        //销毁
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding(charset);
        resp.setCharacterEncoding(charset);
        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        /*初始化方法  接收一个FilterConfig类型的参数 该参数是对Filter的一些配置*/
        filterName = config.getFilterName();
        charset = config.getInitParameter("charset");
    }

}
