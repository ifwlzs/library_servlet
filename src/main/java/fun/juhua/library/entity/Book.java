package fun.juhua.library.entity;

import fun.juhua.library.util.DateUtils;

import java.util.Date;

/**
 * @program: library
 * @description: 书籍实体类
 * @author:
 * @create: 2021-10-25 10:57
 **/
public class Book {
    //书籍ID
    private String bookID;
    //书名
    private String bookName;
    //书籍作者
    private String bookAuthor;
    //出版社
    private String bookPublisher;
    //出版日期
    private Date publishTime;
    //单价
    private float bookPrice;
    //总数
    private int bookSum;
    //借出数量
    private int bookLend;
    //书籍类型
    private String tag;
    //ISBN编号
    private String isbn;

    public Book(String bookID, String bookName, String bookAuthor, String bookPublisher, Date publishTime, float bookPrice, int bookSum, int bookLend, String tag, String isbn) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookPublisher = bookPublisher;
        this.publishTime = publishTime;
        this.bookPrice = bookPrice;
        this.bookSum = bookSum;
        this.bookLend = bookLend;
        this.tag = tag;
        this.isbn = isbn;
    }

    public Book(String bookID, String bookName, String bookAuthor, String bookPublisher, String publishTime, String bookPrice, String bookSum, String bookLend, String tag, String isbn) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookPublisher = bookPublisher;
        this.publishTime = new DateUtils().toDate(publishTime);
        this.bookPrice = Float.parseFloat(bookPrice);
        this.bookSum = Integer.parseInt(bookSum);
        this.bookLend = Integer.parseInt(bookLend);
        this.tag = tag;
        this.isbn = isbn;
    }

    public Book() {
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookID='" + bookID + '\'' +
                ", bookName='" + bookName + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookPublisher='" + bookPublisher + '\'' +
                ", publishTime=" + publishTime +
                ", bookPrice=" + bookPrice +
                ", bookSum=" + bookSum +
                ", bookLend=" + bookLend +
                ", tag='" + tag + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookPublisher() {
        return bookPublisher;
    }

    public void setBookPublisher(String bookPublisher) {
        this.bookPublisher = bookPublisher;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public float getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(float bookPrice) {
        this.bookPrice = bookPrice;
    }

    public int getBookSum() {
        return bookSum;
    }

    public void setBookSum(int bookSum) {
        this.bookSum = bookSum;
    }

    public int getBookLend() {
        return bookLend;
    }

    public void setBookLend(int bookLend) {
        this.bookLend = bookLend;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
