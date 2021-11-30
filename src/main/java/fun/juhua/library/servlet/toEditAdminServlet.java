package fun.juhua.library.servlet;
/**
 * 查询信息并跳转到管理员信息修改界面
 */

import fun.juhua.library.dao.impl.AdminDAOImpl;
import fun.juhua.library.entity.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/toEditAdminServlet")
public class toEditAdminServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Admin admin = null;
        String id = request.getParameter("id");
        if (id != null && id != "") {
            AdminDAOImpl adminDAO = new AdminDAOImpl();
            admin = adminDAO.getAdminByID(id);
        } else {
            admin = (Admin) request.getSession().getAttribute("user");
        }
        request.setAttribute("editUser", admin);
        //System.out.println("toEditAdminServlet -> doPost(25): " + admin);
        request.getRequestDispatcher("/admin/editAdmin.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
