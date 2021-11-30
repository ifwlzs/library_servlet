package fun.juhua.library.servlet;
/**
 * @Description: 判断用户是否登录，已经登录自动跳转对应index。否则跳转到login.jsp
 * @Author: 2020031001
 * @Date: 13:17
 */

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/toIndexServlet")
public class toIndexServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
//        System.out.println(role);
        if (role != null) {
            Cookie cookie = new Cookie("JSESSIONID", session.getId());
            cookie.setMaxAge(60 * 30);
            cookie.setPath("/");
            response.addCookie(cookie);
            if ("admin".equals(role)) {
//                System.out.println("admin");
                request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
            } else {
//                System.out.println("reader");
                request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("/login.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
