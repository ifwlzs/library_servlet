package fun.juhua.library.servlet;
/**
 * 跳转显示读者查询个人借阅信息界面
 */

import fun.juhua.library.dao.impl.BorrowDAOImpl;
import fun.juhua.library.entity.Borrow;
import fun.juhua.library.entity.Reader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/reader/toReaderBorrowServlet")
public class toReaderBorrowServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BorrowDAOImpl borrowDAO = new BorrowDAOImpl();
        List<Borrow> borrowList = null;
        Reader reader = (Reader) request.getSession().getAttribute("user");
        //System.out.println("toReaderBorrowServlet -> doPost(22): " + reader);
        //是否只查询未归还的数据
        String isNull = request.getParameter("isNull");
        //borrowDAO.getBorrowByBookID()
        //当isNull为空，说明显示全部
        if ("T".equals(isNull)) {
            borrowList = borrowDAO.getUnreturnByReaderID(reader.getId());
        } else {
            borrowList = borrowDAO.getBorrowByReaderID(reader.getId());
        }
        //查询的结果传入request域中
        request.setAttribute("borrowList", borrowList);
        request.setAttribute("user", reader);
        //System.out.println("toReaderBorrowServlet -> doPost(34): borrowList:---↓");
        //borrowList.stream().forEach(s -> System.out.println(s));
        //转发
        request.getRequestDispatcher("/reader/borrowList.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
