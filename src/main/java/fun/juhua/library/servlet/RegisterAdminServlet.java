package fun.juhua.library.servlet;
/**
 * 实现注册管理员
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

@WebServlet("/admin/RegisterAdminServlet")
public class RegisterAdminServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String password = request.getParameter("pass");
        String gender = request.getParameter("gender");
        String telephone = request.getParameter("telephone");
        String email = request.getParameter("email");
        //System.out.println(id + " " + name + " " + password + " " + gender + " " + telephone + " " + email);
        Admin admin = new Admin(id, name, password, gender, telephone, email);
        //System.out.println("RegisterAdminServlet -> doPost(27): " + admin);
        AdminDAOImpl adminDAO = new AdminDAOImpl();
        Admin find = adminDAO.getAdminByID(id);
        //创建JSON对象，返回状态
        JSONObject jsonObject = new JSONObject();
        response.setContentType("application/json");
        if (find == null) {
            int raw = adminDAO.addAdmin(admin);
            if (raw != 0) {
                //System.out.println("RegisterAdminServlet -> doPost(36): " + "添加成功"+admin);
                jsonObject.accumulate("state", 1).accumulate("msg", "添加成功");
                response.getWriter().write(jsonObject.toString());
            } else {
                jsonObject.accumulate("state", 0).accumulate("msg", "请重试！");
                response.getWriter().write(jsonObject.toString());
            }
        } else {
            jsonObject.accumulate("state", 0).accumulate("msg", "用户名已存在！");
            response.getWriter().write(jsonObject.toString());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
