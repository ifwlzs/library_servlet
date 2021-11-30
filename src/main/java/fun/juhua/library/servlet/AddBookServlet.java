package fun.juhua.library.servlet;
/**
 * 实现添加书籍功能
 */

import fun.juhua.library.dao.impl.BookDAOImpl;
import fun.juhua.library.entity.Book;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/AddBookServlet")
public class AddBookServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookID = request.getParameter("bookID");
        String bookName = request.getParameter("bookName");
        String bookAuthor = request.getParameter("bookAuthor");
        String bookPublisher = request.getParameter("bookPublisher");
        String publishTime = request.getParameter("publishTime");
        String bookPrice = request.getParameter("bookPrice");
        String bookSum = request.getParameter("bookSum");
        String bookLend = request.getParameter("bookLend");
        String tag = request.getParameter("tag");
        String isbn = request.getParameter("isbn");
        Book book = new Book(bookID, bookName, bookAuthor, bookPublisher, publishTime, bookPrice, bookSum, bookLend, tag, isbn);
        //System.out.println("AddBookServlet -> doPost(29): " + book);
        BookDAOImpl bookDAO = new BookDAOImpl();
        Book find = bookDAO.getBookByID(bookID);
        //插入
        //创建JSON对象，返回状态
        JSONObject jsonObject = new JSONObject();
        response.setContentType("application/json");
        if (find == null) {
            int raw = bookDAO.addBook(book);
            if (raw != 0) {
                //System.out.println("AddBookServlet -> doPost(41): " + "添加成功" + book);
                jsonObject.accumulate("state", 1).accumulate("msg", "添加成功");
                response.getWriter().write(jsonObject.toString());
            } else {
                jsonObject.accumulate("state", 0).accumulate("msg", "请重试！");
                response.getWriter().write(jsonObject.toString());
            }
        } else {
            jsonObject.accumulate("state", 0).accumulate("msg", "书籍ID已存在！");
            response.getWriter().write(jsonObject.toString());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
