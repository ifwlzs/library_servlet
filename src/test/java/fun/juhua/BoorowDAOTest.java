package fun.juhua;

import fun.juhua.library.dao.impl.BookDAOImpl;
import fun.juhua.library.dao.impl.BorrowDAOImpl;
import fun.juhua.library.dao.impl.ReaderDAOImpl;
import fun.juhua.library.entity.Admin;
import fun.juhua.library.entity.Book;
import fun.juhua.library.entity.Borrow;
import fun.juhua.library.entity.Reader;
import fun.juhua.library.util.CommonUtils;
import fun.juhua.library.util.DateUtils;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * @program: library
 * @description: 借阅表sql测试
 * @author:
 * @create: 2021-10-29 10:51
 **/
public class BoorowDAOTest extends CommonUtils<Borrow> {
    private int MAX_LEND_NUM = 8;

    //    //增加借阅信息
//    public Integer addBorrow(Borrow borrow);

    public void addBorrow() {
        Borrow borrow = new Borrow("r001", "b001", new DateUtils().toDate("2021-10-29 11:27:33"), null);
        //System.out.println("BoorowDAOTest -> addBorrow(21): " + borrow);
        Reader reader = new ReaderDAOImpl().getReaderByID(borrow.getReaderID());
        BookDAOImpl bookDAO = new BookDAOImpl();
        Book book = bookDAO.getBookByID(borrow.getBookID());
        //保证书籍和读者存在，且当前读者借书量小于最大值
        if (book != null && reader != null && getAllowByReaderID(borrow.getReaderID())) {
            String sql = "insert into borrow(readerID,bookID,borrowTime) values(?,?,?)";
            int result = CommonUtils.updateCommon(sql, borrow.getReaderID(), borrow.getBookID(), borrow.getBorrowTime());
            //System.out.println("BoorowDAOTest -> addBorrow(35): " + result);
            //借阅信息入表成功后，更改书的借阅数量
            if (result == 1) {
                int raw = bookDAO.borrowBook(book);
                //借书成功
                if (raw == 1) {
                    //System.out.println("BoorowDAOTest -> addBorrow(41): " + 1);
                } else {
                    //借书失败删除借书记录
                    delBorrow();
                    //System.out.println("BoorowDAOTest -> addBorrow(45): " + 0);
                }
            }
        }
        //System.out.println("BoorowDAOTest -> addBorrow(49): " + 0);
    }

    public void delBorrow() {
        Borrow borrow = new Borrow("r111", "b111", new DateUtils().toDate("2021-10-29 11:27:32"), null);
        String sql = "delete from borrow where readerID=? and bookID=? and borrowTime=?";
        //System.out.println(CommonUtils.updateCommon(sql, borrow.getReaderID(), borrow.getBookID(), borrow.getBorrowTime()));
    }

    //    //通过用户id查询借阅信息
//    public List<Borrow> getBorrowByReaderID(String readerID);

    public void getBorrowByReaderID() {
        String sql = "select * from borrow where readerID=?";
        List<Borrow> borrows = super.queryCommon(sql, Borrow.class, "r002");
        //如果取到数据，则返回该对象
        //borrows.stream().forEach(s -> System.out.println(s));
    }
    @Test
    public void getBorrowByBookID(){
        String bookID="b001";
        String sql=" select * from borrow where bookID=? and returnTime is null";
        List<Borrow> borrows = super.queryCommon(sql, Borrow.class, bookID);
        //borrows.stream().forEach(s -> System.out.println(s));
    }
//    //通过书籍id查询借阅信息
//    public List<Borrow> getBorrowByBookID(String bookID);

    //    //查找所有借阅信息
//    public List<Borrow> getAllBorrow();
    public void getAllBorrow() {
        String sql = "select * from borrow ";
        List<Borrow> list = super.queryCommon(sql, Borrow.class);
        if (list.size() > 0) {
            System.out.println("BorrowDAOImpl -> getAllBorrow(68): ");
            //list.stream().forEach((s) -> System.out.println(s));
            // return list.get(0);
        }
        return;
    }

    //通过用户id查询是否可以借书
    //public Boolean getAllowByReaderID(String readerID);
    public Boolean getAllowByReaderID(String readerID) {
        String sql = "select * from borrow where readerID=? and returnTime is null";
        List<Borrow> borrows = super.queryCommon(sql, Borrow.class, readerID);
        //System.out.println("BoorowDAOTest -> getAllowByReaderID(87): " + borrows.size());
        //必须小于最大值才能借书
        return borrows.size() < BorrowDAOImpl.MAX_LEND_NUM;
    }

}
