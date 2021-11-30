package fun.juhua.library.servlet;
/**
 * 实现更新书籍信息
 */

import fun.juhua.library.dao.impl.BookDAOImpl;
import fun.juhua.library.entity.Book;
import fun.juhua.library.util.DateUtils;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/admin/UpdateBookServlet")
public class UpdateBookServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookID = request.getParameter("bookID");
        String bookName = request.getParameter("bookName");
        String bookAuthor = request.getParameter("bookAuthor");
        String bookPublisher = request.getParameter("bookPublisher");
        Date publishTime = new DateUtils().toDate(request.getParameter("publishTime"));
        Float bookPrice = Float.parseFloat(request.getParameter("bookPrice"));
        Integer bookSum = Integer.parseInt(request.getParameter("bookSum"));
        Integer bookLend = Integer.parseInt(request.getParameter("bookLend"));
        String tag = request.getParameter("tag");
        String isbn = request.getParameter("isbn");
        Book book = new Book(bookID, bookName, bookAuthor, bookPublisher, publishTime, bookPrice, bookSum, bookLend, tag, isbn);
        //System.out.println("UpdateBookServlet -> doPost(29): " + book);
        //更新数据库
        int raw = new BookDAOImpl().updateBook(book);
        //创建JSON对象，返回状态
        JSONObject jsonObject = new JSONObject();
        response.setContentType("application/json");
        if (raw == 1) {
            jsonObject.accumulate("state", 1).accumulate("msg", "更新成功");
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
