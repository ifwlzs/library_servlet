package fun.juhua.library.servlet;
/**
 * 查询信息并跳转到书籍列表（读者）界面
 */

import fun.juhua.library.dao.impl.BookDAOImpl;
import fun.juhua.library.dao.impl.BorrowDAOImpl;
import fun.juhua.library.entity.Book;
import fun.juhua.library.entity.Reader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/reader/toBookshelfServlet")
public class toBookshelfServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookDAOImpl bookDAO = new BookDAOImpl();
        String bookName = request.getParameter("bookName");
        //根据书籍id查询
        String bookID = request.getParameter("bookID");
        Reader reader = (Reader) request.getSession().getAttribute("user");
        List<Book> bookList = null;
        //System.out.println("toBookListServlet -> doPost(22): " + bookName);
        //无传参，全部显示
        if ((bookName == null || bookName == "") && (bookID == null || bookID == "")) {
            bookList = bookDAO.getAllBook();
        } else if ((bookID == null || bookID == "")) {
            bookList = bookDAO.getBookByName(bookName);
        } else {
            bookList = new ArrayList<>();
            bookList.add(bookDAO.getBookByID(bookID));
        }
        //System.out.println("toBookListServlet -> doPost(20): " + Arrays.toString(bookList.toArray()));
        request.setAttribute("bookList", bookList);
        //塞入是否可借书变量
        request.setAttribute("allow", new BorrowDAOImpl().getAllowByReaderID(reader.getId()));
        //转发
        request.getRequestDispatcher("/reader/bookList.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
