package fun.juhua.library.servlet;
/**
 * 查询信息并跳转到读者列表界面
 */

import fun.juhua.library.dao.impl.ReaderDAOImpl;
import fun.juhua.library.entity.Reader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/toReaderListServlet")
public class toReaderListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReaderDAOImpl readerDAO = new ReaderDAOImpl();
        String name = request.getParameter("name");
        //根据id查询
        String id = request.getParameter("id");
        //System.out.println("toReaderListServlet -> doPost(22): " + name);
        List<Reader> readerList = null;
        if ((name == null || name == "") && (id == null || id == "")) {
            readerList = readerDAO.getAllReader();
        } else if (id == null || id == "") {
            //如果传来姓名值，说明要按名模糊查找
            readerList = readerDAO.getReaderByName(name);
        } else {
            readerList = new ArrayList<>();
            readerList.add(readerDAO.getReaderByID(id));
        }
        //System.out.println("toReaderListServlet -> doPost(21): " + Arrays.toString(readerList.toArray()));
        //查询的结果传入request域中
        request.setAttribute("readerList", readerList);
        //转发
        request.getRequestDispatcher("/admin/readerList.jsp").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
