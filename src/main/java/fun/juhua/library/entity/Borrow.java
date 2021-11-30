package fun.juhua.library.entity;

import fun.juhua.library.util.DateUtils;

import java.util.Date;

/**
 * @program: library
 * @description: 借书记录实体类
 * @author:
 * @create: 2021-10-25 11:13
 **/
public class Borrow {
    //读者id
    private String readerID;
    //书籍id
    private String bookID;
    //借出时间
    private Date borrowTime;
    //归还时间
    private Date returnTime;

    public Borrow(String readerID, String bookID, Date borrowTime, Date returnTime) {
        this.readerID = readerID;
        this.bookID = bookID;
        this.borrowTime = borrowTime;
        this.returnTime = returnTime;
    }

    public Borrow(String readerID, String bookID, String borrowTime, Date returnTime) {
        this.readerID = readerID;
        this.bookID = bookID;
        this.borrowTime = new DateUtils().toDate(borrowTime);
        this.returnTime = returnTime;
    }

    public Borrow(String readerID, String bookID, String borrowTime) {
        this.readerID = readerID;
        this.bookID = bookID;
        this.borrowTime = new DateUtils().toDate(borrowTime);
        this.returnTime = null;
    }

    public Borrow(String readerID, String bookID, Date borrowTime) {
        this.readerID = readerID;
        this.bookID = bookID;
        this.borrowTime = borrowTime;
        this.returnTime = null;
    }

    public Borrow() {
    }

    @Override
    public String toString() {
        return "borrow{" +
                "readerID='" + readerID + '\'' +
                ", bookID='" + bookID + '\'' +
                ", borrowTime=" + borrowTime +
                ", returnTime=" + returnTime +
                '}';
    }

    public String getReaderID() {
        return readerID;
    }

    public void setReaderID(String readerID) {
        this.readerID = readerID;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public Date getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(Date borrowTime) {
        this.borrowTime = borrowTime;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }
}
