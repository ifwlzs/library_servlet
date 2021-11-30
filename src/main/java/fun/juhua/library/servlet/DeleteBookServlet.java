package fun.juhua.library.servlet;
/**
 * 实现删除书籍功能
 */

import fun.juhua.library.dao.impl.BookDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/DeleteBookServlet")
public class DeleteBookServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookID = request.getParameter("id");
        //System.out.println("DeleteBookServlet -> doPost(15): " + bookID);
        int raw = new BookDAOImpl().delBookByID(bookID);
        //System.out.println("DeleteBookServlet -> doPost(19): " + raw);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
