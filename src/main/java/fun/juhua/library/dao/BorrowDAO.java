package fun.juhua.library.dao;

import fun.juhua.library.entity.Borrow;

import java.util.Date;
import java.util.List;

/**
 * @program: library
 * @description:
 * @author:
 * @create: 2021-10-26 12:15
 **/
public interface BorrowDAO {
    //增加借阅信息
    public Integer addBorrow(Borrow borrow);

    //删除某条记录
    public Integer delBorrow(Borrow borrow);

    //删除某条记录
    public Integer delBorrow(String readerID, String bookID, Date borrowTime);

    //删除某条记录
    public Integer delBorrow(String readerID, String bookID, String borrowTime);

    //通过id更改借阅信息(还书)
    public Integer updateBorrow(Borrow borrow);

    //通过用户id查询借阅信息
    public List<Borrow> getBorrowByReaderID(String readerID);

    //通过用户id查询，未还书籍
    public List<Borrow> getUnreturnByReaderID(String readerID);

    //通过书籍id查询借阅信息
    public List<Borrow> getBorrowByBookID(String bookID);

    //通过书籍id查询，未还书籍
    public List<Borrow> getUnreturnByBookID(String bookID);

    //查找所有借阅信息
    public List<Borrow> getAllBorrow();

    //通过用户id查询已借数量
    public Integer getBorrowNumByReaderID(String readerID);

    //通过用户id查询是否可以借书
    public Boolean getAllowByReaderID(String readerID);

    //返还未归还的书籍
    public List<Borrow> getUnreturnBook();
}
