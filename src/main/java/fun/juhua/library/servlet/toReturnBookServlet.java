package fun.juhua.library.servlet;
/**
 * 查询信息并实现还书功能
 */

import fun.juhua.library.dao.impl.BorrowDAOImpl;
import fun.juhua.library.entity.Borrow;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/admin/toReturnBookServlet")
public class toReturnBookServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String readerID = request.getParameter("readerID");
        String bookID = request.getParameter("bookID");
        String borrowTime = request.getParameter("borrowTime");
        BorrowDAOImpl borrowDAO = new BorrowDAOImpl();
        Borrow borrow = new Borrow(readerID, bookID, borrowTime, new Date());
        System.out.println("toReturnBookServlet -> doPost(17): " + borrow);
        int raw = borrowDAO.updateBorrow(borrow);
        //System.out.println("toReturnBookServlet -> doPost(25): " + raw);
        JSONObject jsonObject = new JSONObject();
        response.setContentType("application/json");
        if (raw == 1) {
            jsonObject.accumulate("state", 1).accumulate("msg", "归还成功！");
            response.getWriter().write(jsonObject.toString());
        } else {
            jsonObject.accumulate("state", 0).accumulate("msg", "归还失败，请重试！");
            response.getWriter().write(jsonObject.toString());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
