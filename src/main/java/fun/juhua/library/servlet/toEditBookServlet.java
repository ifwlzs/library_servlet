package fun.juhua.library.servlet;
/**
 * 查询信息并跳转到书籍修改界面
 */

import fun.juhua.library.dao.impl.BookDAOImpl;
import fun.juhua.library.entity.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/toEditBookServlet")
public class toEditBookServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookID = request.getParameter("id");
        Book book = new BookDAOImpl().getBookByID(bookID);
        //System.out.println("toEditBookServlet -> doPost(20): " + book);
        request.setAttribute("editBook", book);
        request.getRequestDispatcher("/admin/editBook.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
