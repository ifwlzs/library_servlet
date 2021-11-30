package fun.juhua.library.dao.impl;

import fun.juhua.library.dao.BookDAO;
import fun.juhua.library.entity.Admin;
import fun.juhua.library.entity.Book;
import fun.juhua.library.util.CommonUtils;

import java.util.List;

/**
 * @program: library
 * @description: BookDAO实现类
 * @author:
 * @create: 2021-10-26 12:16
 **/
public class BookDAOImpl extends CommonUtils<Book> implements BookDAO {
    @Override
    public Integer addBook(Book book) {
        String sql = "insert into book(bookID,bookName,bookAuthor,bookPublisher," +
                "publishTime,bookPrice,bookSum,bookLend,tag,isbn) values(?,?,?,?,?,?,?,?,?,?)";
        int result = CommonUtils.updateCommon(sql, book.getBookID(), book.getBookName(), book.getBookAuthor(), book.getBookPublisher()
                , book.getPublishTime(), book.getBookPrice(), book.getBookSum(), book.getBookLend(), book.getTag(), book.getIsbn());
        return result;
    }

    @Override
    public Integer delBook(Book book) {
        return delBookByID(book.getBookID());
    }


    @Override
    public Integer delBookByID(String bookID) {
        String sql = "delete from book where bookID=?";
        return CommonUtils.updateCommon(sql, bookID);
    }

    @Override
    public Integer updateBook(Book book) {
        String sql = "update book set bookName=?,bookAuthor=?,bookPublisher=?,publishTime=?," +
                "bookPrice=?,bookSum=?,bookLend=?,tag=?,isbn=? where bookID=?";
        int result = CommonUtils.updateCommon(sql, book.getBookName(), book.getBookAuthor(), book.getBookPublisher()
                , book.getPublishTime(), book.getBookPrice(), book.getBookSum(), book.getBookLend(), book.getTag(), book.getIsbn(), book.getBookID());
        return result;
    }

    @Override
    public Integer borrowBook(String bookID) {
        String sql = "update book set bookLend=? where bookID=?";
        Book book = getBookByID(bookID);
        //如果库存小于等于借出数量，返回不成功
        if (book.getBookSum() <= book.getBookLend()) {
            return 0;
        }
        int result = CommonUtils.updateCommon(sql, book.getBookLend() + 1, bookID);
        return result;
    }

    @Override
    public Integer borrowBook(Book book) {
        String sql = "update book set bookLend=? where bookID=?";
        //如果库存小于等于借出数量，返回不成功
        if (book.getBookSum() <= book.getBookLend() || book.getBookLend() < 0) {
            return 0;
        }
        //System.out.println("BookDAOImpl -> borrowBook(66): " + book);
        int result = CommonUtils.updateCommon(sql, book.getBookLend() + 1, book.getBookID());
        return result;
    }

    @Override
    public Integer returnBook(String bookID) {
        String sql = "update book set bookLend=? where bookID=?";
        Book book = getBookByID(bookID);
        //System.out.println("BookDAOImpl -> returnBook(75): " + book);
        //如果借出数量大于库存,已借数量小于等于0，返回不成功
        if (book.getBookSum() < book.getBookLend() || book.getBookLend() < 1) {
            return 0;
        }
        int result = CommonUtils.updateCommon(sql, book.getBookLend() - 1, bookID);
        return result;
    }

    @Override
    public Integer returnBook(Book book) {
        String sql = "update book set bookLend=? where bookID=?";
        //如果借出数量大于库存，返回不成功
        if (book.getBookSum() < book.getBookLend()) {
            return 0;
        }
        int result = CommonUtils.updateCommon(sql, book.getBookLend() - 1, book.getBookID());
        return result;
    }

    @Override
    public List<Book> getAllBook() {
        String sql = "select * from book";
        List<Book> books = super.queryCommon(sql, Book.class);
        return books;
    }

    @Override
    public Book getBookByID(String bookID) {
        String sql = "select * from book where bookID=?";
        List<Book> list = super.queryCommon(sql, Book.class, bookID);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<Book> getBookByName(String bookName) {
        String sql = "select * from book where bookName LIKE ?";
        List<Book> list = super.queryCommon(sql, Book.class, "%" + bookName + "%");
        return list;
    }
}
