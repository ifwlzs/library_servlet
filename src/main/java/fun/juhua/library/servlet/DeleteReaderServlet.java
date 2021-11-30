package fun.juhua.library.servlet;
/**
 * 实现删除读者功能
 */

import fun.juhua.library.dao.impl.ReaderDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/DeleteReaderServlet")
public class DeleteReaderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        //System.out.println("DeleteReaderServlet -> doPost(17): 删除id" + id);
        Integer raw = new ReaderDAOImpl().delReaderByID(id);
        //System.out.println("DeleteReaderServlet  -> doPost(20): " + raw);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
