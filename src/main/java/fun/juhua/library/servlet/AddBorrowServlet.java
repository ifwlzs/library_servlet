package fun.juhua.library.servlet;
/**
 * 实现添加借书记录功能
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

@WebServlet("/admin/AddBorrowServlet")
public class AddBorrowServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String readerID = request.getParameter("readerID");
        String bookID = request.getParameter("bookID");
        String borrowTime = request.getParameter("borrowTime");
        Borrow borrow = new Borrow(readerID, bookID, borrowTime);
        //System.out.println("AddBookServlet -> doPost(25): " + borrow);
        BorrowDAOImpl borrowDao = new BorrowDAOImpl();
        //创建JSON对象，返回状态
        JSONObject jsonObject = new JSONObject();
        response.setContentType("application/json");
        int raw = borrowDao.addBorrow(borrow);
        if (raw != 0) {
            //System.out.println("AddBorrowServlet -> doPost(31): " + "借书成功" + borrow);
            jsonObject.accumulate("state", 1).accumulate("msg", "借书成功");
            response.getWriter().write(jsonObject.toString());
        } else {
            jsonObject.accumulate("state", 0).accumulate("msg", "达到最大借书数量！请尽快归还！");
            response.getWriter().write(jsonObject.toString());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
