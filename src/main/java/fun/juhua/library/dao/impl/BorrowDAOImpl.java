package fun.juhua.library.dao.impl;

import fun.juhua.library.dao.BorrowDAO;
import fun.juhua.library.entity.Book;
import fun.juhua.library.entity.Borrow;
import fun.juhua.library.entity.Reader;
import fun.juhua.library.util.CommonUtils;
import fun.juhua.library.util.DateUtils;

import java.util.Date;
import java.util.List;

/**
 * @program: library
 * @description: BorrowDAO实现类
 * @author:
 * @create: 2021-10-26 12:16
 **/
public class BorrowDAOImpl extends CommonUtils<Borrow> implements BorrowDAO {
    //设置最大借书数量
    static final public Integer MAX_LEND_NUM = 8;

    @Override
    public Integer addBorrow(Borrow borrow) {
        //System.out.println("BorrowDAOImpl -> addBorrow(25)borrow: " + borrow);
        Reader reader = new ReaderDAOImpl().getReaderByID(borrow.getReaderID());
        BookDAOImpl bookDAO = new BookDAOImpl();
        Book book = bookDAO.getBookByID(borrow.getBookID());
        //保证书籍和读者存在，且当前读者借书量小于最大值
        if (book != null && reader != null && getAllowByReaderID(borrow.getReaderID())) {
            String sql = "insert into borrow(readerID,bookID,borrowTime) values(?,?,?)";
            int result = CommonUtils.updateCommon(sql, borrow.getReaderID(), borrow.getBookID(), borrow.getBorrowTime());
            //System.out.println("BorrowDAOImpl -> addBorrow(33)result: " + result);
            //借阅信息成功入表后，更改书的借阅数量
            if (result == 1) {
                int raw = bookDAO.borrowBook(book);
                //借书成功
                if (raw == 1) {
                    return 1;
                } else {
                    //借书失败删除借书记录
                    delBorrow(borrow);
                    return 0;
                }
            }
        }
        return 0;
    }

    @Override
    public Integer delBorrow(Borrow borrow) {
        String sql = "delete from borrow where readerID=? and bookID=? and borrowTime=?";
        int result = CommonUtils.updateCommon(sql, borrow.getReaderID(), borrow.getBookID(), borrow.getBorrowTime());
        return result;
    }

    @Override
    public Integer delBorrow(String readerID, String bookID, Date borrowTime) {
        String sql = "delete from borrow where readerID=? and bookID=? and borrowTime=?";
        int result = CommonUtils.updateCommon(sql, readerID, bookID, borrowTime);
        return result;
    }

    @Override
    public Integer delBorrow(String readerID, String bookID, String borrowTime) {
        String sql = "delete from borrow where readerID=? and bookID=? and borrowTime=?";
        int result = CommonUtils.updateCommon(sql, readerID, bookID, new DateUtils().toDate(borrowTime));
        return result;
    }

    //还书
    @Override
    public Integer updateBorrow(Borrow borrow) {
        String sql = "update borrow set returnTime=? where readerID=? and bookID=? and borrowTime=?";
        //满足归还条件再去判断
        int raw = new BookDAOImpl().returnBook(borrow.getBookID());
        //System.out.println("BorrowDAOImpl -> updateBorrow(76): " + raw);
        if (raw == 1) {
            int result = CommonUtils.updateCommon(sql, borrow.getReturnTime(), borrow.getReaderID(), borrow.getBookID(), borrow.getBorrowTime());
            return result;
        }
        return raw;
    }

    @Override
    public List<Borrow> getBorrowByReaderID(String readerID) {
        String sql = "select * from borrow where readerID=?";
        List<Borrow> borrows = super.queryCommon(sql, Borrow.class, readerID);
        //如果取到数据，则返回该对象
        //borrows.stream().forEach(s-> System.out.println(s));
        return borrows;
    }

    @Override
    public List<Borrow> getUnreturnByReaderID(String readerID) {
        String sql = " select * from borrow where readerID=? and returnTime is null";
        List<Borrow> borrows = super.queryCommon(sql, Borrow.class, readerID);
        return borrows;
    }

    @Override
    public List<Borrow> getBorrowByBookID(String bookID) {
        String sql = " select * from borrow where bookID=?";
        List<Borrow> borrows = super.queryCommon(sql, Borrow.class, bookID);
        return borrows;
    }

    @Override
    public List<Borrow> getUnreturnByBookID(String bookID) {
        String sql = "select * from borrow where bookID=? and returnTime is null";
        List<Borrow> borrows = super.queryCommon(sql, Borrow.class, bookID);
        //如果取到数据，则返回该对象
        //borrows.stream().forEach(s-> System.out.println(s));
        return borrows;
    }

    @Override
    public List<Borrow> getAllBorrow() {
        String sql = "select * from borrow";
        List<Borrow> list = super.queryCommon(sql, Borrow.class);
        return list;
    }

    @Override
    public Integer getBorrowNumByReaderID(String readerID) {
        String sql = "select * from borrow where readerID=? ";
        List<Borrow> borrows = super.queryCommon(sql, Borrow.class, readerID);
        //System.out.println("BorrowDAOImpl -> getBorrowNumByReaderID(87): " + borrows.size());
        return borrows.size();
    }

    @Override
    public Boolean getAllowByReaderID(String readerID) {
        //必须小于最大值才能借书
        return getBorrowNumByReaderID(readerID) < MAX_LEND_NUM;
    }

    @Override
    public List<Borrow> getUnreturnBook() {
        String sql = "select * from borrow where returnTime is null";
        List<Borrow> borrows = super.queryCommon(sql, Borrow.class);
        //如果取到数据，则返回该对象
        //borrows.stream().forEach(s-> System.out.println(s));
        return borrows;
    }
}
