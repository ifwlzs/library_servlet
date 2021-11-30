package fun.juhua.library.servlet;
/**
 * 实现删除管理员功能
 */

import fun.juhua.library.dao.impl.AdminDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/DeleteAdminServlet")
public class DeleteAdminServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        //System.out.println("DeleteAdminServlet -> doPost(15): 删除id" + id);
        Integer raw = new AdminDAOImpl().delAdminByID(id);
        //System.out.println("DeleteAdminServlet -> doPost(20): " + raw);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
