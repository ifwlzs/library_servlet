package fun.juhua.library.servlet;
/**
 * 查询信息并跳转到借阅信息列表界面
 */

import fun.juhua.library.dao.impl.BorrowDAOImpl;
import fun.juhua.library.entity.Borrow;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/toBorrowListServlet")
public class toBorrowListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BorrowDAOImpl borrowDAO = new BorrowDAOImpl();
        List<Borrow> borrowList = null;
        //根据id查找借阅信息
        String bookID = request.getParameter("bookID");
        String readerID = request.getParameter("readerID");
        //是否只查询未归还的数据
        String isNull = request.getParameter("isNull");
        //borrowDAO.getBorrowByBookID()
        //当bookID和readerID都为空，说明显示全部
        if ((bookID == null || bookID == "") && (readerID == null || readerID == "")) {
            //如果isNull为T，查询未归还的书籍
            if ("T".equals(isNull)) {
                borrowList = borrowDAO.getUnreturnBook();
            } else {
                borrowList = borrowDAO.getAllBorrow();
            }
        } else if (bookID == null || bookID == "") {
            //readerID不为空，查询某人所有借阅记录
            //如果isNull为T，查询此人未归还的书籍
            if ("T".equals(isNull)) {
                borrowList = borrowDAO.getUnreturnByReaderID(readerID);
            } else {
                borrowList = borrowDAO.getBorrowByReaderID(readerID);
            }
        } else {
            //bookID不为空，查询某人所有借阅记录
            //如果isNull为T，查询此人未归还的书籍
            if ("T".equals(isNull)) {
                borrowList = borrowDAO.getUnreturnByBookID(bookID);
            } else {
                borrowList = borrowDAO.getBorrowByBookID(bookID);
            }
        }
        //查询的结果传入request域中
        request.setAttribute("borrowList", borrowList);
        //System.out.println("toBorrowListServlet -> doPost(60): borrowList:---↓");
        //borrowList.stream().forEach(s -> System.out.println(s));
        //转发
        request.getRequestDispatcher("/admin/borrowList.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
