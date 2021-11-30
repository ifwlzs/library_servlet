package fun.juhua.library.servlet;
/**
 * 查询信息并跳转到读者修改信息界面
 */

import fun.juhua.library.dao.impl.ReaderDAOImpl;
import fun.juhua.library.entity.Reader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/toEditReaderServlet")
public class toEditReaderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Reader reader = null;
        String id = request.getParameter("id");
        if (id != null && id != "") {
            ReaderDAOImpl readerDAO = new ReaderDAOImpl();
            reader = readerDAO.getReaderByID(id);
            request.setAttribute("editUser", reader);
            request.getRequestDispatcher("/admin/editReader.jsp").forward(request, response);
        } else {
            reader = (Reader) request.getSession().getAttribute("user");
            request.setAttribute("user", reader);
            request.getRequestDispatcher("/reader/edit.jsp").forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
