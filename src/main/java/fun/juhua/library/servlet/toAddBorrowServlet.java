package fun.juhua.library.servlet;
/**
 * 跳转到借书登记界面
 */

import fun.juhua.library.dao.impl.BookDAOImpl;
import fun.juhua.library.dao.impl.ReaderDAOImpl;
import fun.juhua.library.entity.Book;
import fun.juhua.library.entity.Reader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/toAddBorrowServlet")
public class toAddBorrowServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Reader> readerList = new ReaderDAOImpl().getAllReader();
        List<Book> bookList = new BookDAOImpl().getAllBook();
        request.setAttribute("readerList", readerList);
        request.setAttribute("bookList", bookList);
        request.getRequestDispatcher("/admin/addBorrow.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
