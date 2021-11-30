package fun.juhua.library.dao;

import fun.juhua.library.entity.Book;
import fun.juhua.library.entity.Borrow;

import java.util.List;

public interface BookDAO {
    //增加书籍
    public Integer addBook(Book book);

    //删除书籍
    public Integer delBook(Book book);

    //借出书籍
    public Integer borrowBook(String bookID);

    //归还书籍
    public Integer returnBook(String bookID);

    //借出书籍
    public Integer borrowBook(Book book);

    //归还书籍
    public Integer returnBook(Book book);

    //通过bookID删除书籍
    public Integer delBookByID(String bookID);

    //更新书籍信息
    public Integer updateBook(Book book);

    //查询所有书籍信息
    public List<Book> getAllBook();

    //通过bookID查找书籍
    public Book getBookByID(String bookID);

    //通过bookName查找书籍
    public List<Book> getBookByName(String bookName);
}
