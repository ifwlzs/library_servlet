package fun.juhua;

import fun.juhua.library.entity.Admin;
import fun.juhua.library.entity.Book;
import fun.juhua.library.entity.Reader;
import fun.juhua.library.util.CommonUtils;
import org.junit.Test;

import java.util.List;

/**
 * @program: library
 * @description: sql查询测试类
 * @author:
 * @create: 2021-10-28 16:55
 **/
public class SQLFindTest extends CommonUtils {
    @Test
    public void selectPassword() {
        String sql = "select * from reader where id=? ";
        String id = "r001";
        List<Reader> readers = super.queryCommon(sql, Reader.class, id);
        //System.out.println(readers.get(0).getPassword());

    }



    @Test
    public void getAllBook() {
        String sql = "select * from book";
        List<Book> books = super.queryCommon(sql, Book.class);
        //books.stream().forEach((s) -> System.out.println(s));
    }

    @Test
    public void getBookByID() {
        String sql = "select * from book where bookID=?";
        List<Book> books = super.queryCommon(sql, Book.class, "b001");
        //books.stream().forEach((s) -> System.out.println(s));
    }

    @Test
    public void findBookByName() {
        String sql = "select * from book where bookName LIKE ?";
        List<Book> books = super.queryCommon(sql, Book.class, "%" + "兵" + "%");
        //books.stream().forEach((s) -> System.out.println(s));
    }

    @Test
    public void findAdminByName() {
        String sql = "select * from admin where name LIKE ?";
        List<Admin> list = super.queryCommon(sql, Admin.class, "%" + "1" + "%");
        //list.stream().forEach((s) -> System.out.println(s));
    }

    @Test
    public void findReaderByName() {
        String sql = "select * from reader where name LIKE ?";
        List<Reader> list = super.queryCommon(sql, Reader.class, "%" + "1" + "%");
        //list.stream().forEach((s) -> System.out.println(s));
    }

}
