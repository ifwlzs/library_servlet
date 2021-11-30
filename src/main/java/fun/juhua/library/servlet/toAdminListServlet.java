package fun.juhua.library.servlet;
/**
 * 查询信息并跳转到管理员列表界面
 */

import fun.juhua.library.dao.impl.AdminDAOImpl;
import fun.juhua.library.entity.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/admin/toAdminListServlet")
public class toAdminListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminDAOImpl adminDAO = new AdminDAOImpl();
        String name = request.getParameter("name");
        List<Admin> adminList = null;
        //如果传来值，说明要按名模糊查找
        if (name == null || name == "") {
            adminList = adminDAO.getAllAdmin();
        } else {
            adminList = adminDAO.getAdminByName(name);
        }
        //System.out.println("toAdminListServlet -> doPost(20): " + Arrays.toString(adminList.toArray()));
        //查询的结果传入request域中
        request.setAttribute("adminList", adminList);
        //转发
        request.getRequestDispatcher("/admin/adminList.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
