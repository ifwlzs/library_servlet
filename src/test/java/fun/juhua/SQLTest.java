package fun.juhua;

import fun.juhua.library.entity.Admin;
import fun.juhua.library.entity.Book;
import fun.juhua.library.util.CommonUtils;
import org.junit.Test;

import java.util.Date;

/**
 * @program: library
 * @description: sql测试类
 * @author:
 * @create: 2021-10-26 10:14
 **/
public class SQLTest extends CommonUtils {


    @Test
    public void updateAdmin() {
        Admin newAdmin = new Admin("m002", "ss", "m001", "男", "17330000000", "1@qq.com");
        String sql = "update admin set name=?,password=?,gender=?,telephone=?,email=? where id=?";
        int result = CommonUtils.updateCommon(sql, newAdmin.getName(), newAdmin.getPassword(), newAdmin.getGender()
                , newAdmin.getTelephone(), newAdmin.getEmail(), newAdmin.getId());
        //System.out.println(result);
    }

    @Test
    public void addAdmin() {
        Admin addAdmin = new Admin("m002", "ss", "m002", "男", "17330000000", "1@qq.com");
        String sql = "insert into admin(id,name,password,gender,telephone,email) values(?,?,?,?,?,?)";
        int result = CommonUtils.updateCommon(sql, addAdmin.getId(), addAdmin.getName(), addAdmin.getPassword(), addAdmin.getGender()
                , addAdmin.getTelephone(), addAdmin.getEmail());
        //System.out.println(result);

    }

    @Test
    public void delAdminByID() {
        String id = "m002";
        String sql = "delete from admin where id=?";
        //System.out.println(CommonUtils.updateCommon(sql, id));
    }

    @Test
    public void addBook() {
        Book book = new Book("b011", "1", "2", "3", new Date(), (float) 15.20, 9, 9, "tag", "1111");
        //System.out.println("SQLTest -> addBook(51): " + book);
        String sql = "insert into book(bookID,bookName,bookAuthor,bookPublisher," +
                "publishTime,bookPrice,bookSum,bookLend,tag,isbn) values(?,?,?,?,?,?,?,?,?,?)";
        int result = CommonUtils.updateCommon(sql, book.getBookID(), book.getBookName(), book.getBookAuthor(), book.getBookPublisher()
                , book.getPublishTime(), book.getBookPrice(), book.getBookSum(), book.getBookLend(), book.getTag(), book.getIsbn());
        //System.out.println("SQLTest -> addBook(66): " + result);
    }

    @Test
    public void delBook() {
        String sql = "delete from book where bookID=?";
        //System.out.println(CommonUtils.updateCommon(sql, "1"));
    }

    @Test
    public void updateBook() {
        Book book = new Book("b011", "1", "2", "3", new Date(), (float) 15.20, 9, 9, "tag", "1111");

        String sql = "update book set bookName=?,bookAuthor=?,bookPublisher=?,publishTime=?," +
                "bookPrice=?,bookSum=?,bookLend=?,tag=?,isbn=? where bookID=?";
        int result = CommonUtils.updateCommon(sql, book.getBookName(), book.getBookAuthor(), book.getBookPublisher()
                , book.getPublishTime(), book.getBookPrice(), book.getBookSum(), book.getBookLend(), book.getTag(), book.getIsbn(), book.getBookID());
        //System.out.println(result);
    }


}
