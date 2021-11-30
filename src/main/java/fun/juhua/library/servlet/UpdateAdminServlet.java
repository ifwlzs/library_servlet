package fun.juhua.library.servlet;
/**
 * 实现更新管理员信息
 */

import fun.juhua.library.dao.impl.AdminDAOImpl;
import fun.juhua.library.entity.Admin;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/UpdateAdminServlet")
public class UpdateAdminServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String password = request.getParameter("pass");
        String oldPassword = request.getParameter("oldpass");
        String gender = request.getParameter("gender");
        String telephone = request.getParameter("telephone");
        String email = request.getParameter("email");
        Admin admin = new Admin(id, name, password == "" ? oldPassword : password, gender, telephone, email);
        //System.out.println("UpdateAdminServlet -> doPost(25): " + admin);
        //更新数据库
        int raw = new AdminDAOImpl().updateAdmin(admin);
        //创建JSON对象，返回状态
        JSONObject jsonObject = new JSONObject();
        response.setContentType("application/json");
        //返还状态
        if (raw == 1) {
            jsonObject.accumulate("state", 1).accumulate("msg", "更新成功!");
            response.getWriter().write(jsonObject.toString());
        } else {
            jsonObject.accumulate("state", 0).accumulate("msg", "更新失败，请重试");
            response.getWriter().write(jsonObject.toString());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
