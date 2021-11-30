package fun.juhua.library.servlet;
/**
 * 实现登录功能，如果已经登录了，自动跳转对应index.jsp，
 * 如果没有 就访问login.jsp
 */

import fun.juhua.library.dao.impl.AdminDAOImpl;
import fun.juhua.library.dao.impl.ReaderDAOImpl;
import fun.juhua.library.entity.Reader;
import fun.juhua.library.entity.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        //System.out.println("LoginServlet -> doPost(19): " + id + " " + password);
        String checkAdmin = id.substring(0, 1);
        //System.out.println(checkAdmin);
        if (checkAdmin.equals("m")) {
            AdminDAOImpl adminDAO = new AdminDAOImpl();
            Admin admin = adminDAO.findAdmin(id, password);
            if (admin != null) {
                //验证通过,保存cookie，跳转
                HttpSession session = request.getSession();
                session.setAttribute("user", admin);
                session.setAttribute("role", "admin");
                String rpath = request.getContextPath();
                response.sendRedirect(rpath + "/admin/index.jsp");
            } else {
                //验证失败
                request.setAttribute("errorMsg", "用户名或密码错误");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } else if (checkAdmin.equals("r")) {
            ReaderDAOImpl readerDAO = new ReaderDAOImpl();
            Reader reader = readerDAO.findReader(id, password);
            if (reader != null) {
                //验证通过跳转
                HttpSession session = request.getSession();
                session.setAttribute("user", reader);
                session.setAttribute("role", "reader");
                String rpath = request.getContextPath();
                response.sendRedirect(rpath + "/reader/index.jsp");
            } else {
                //验证失败
                request.setAttribute("errorMsg", "用户名或密码错误");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } else {
            //验证失败
            request.setAttribute("errorMsg", "用户名或密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }


    }

    public void toIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
