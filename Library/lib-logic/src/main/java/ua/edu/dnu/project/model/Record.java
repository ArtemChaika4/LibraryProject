package ua.edu.dnu.project.model;

import ua.edu.dnu.project.db.DBEntry;

import java.sql.Date;

public class Record extends DBEntry {
    private Book book;
    private User user;
    private Date dateOfIssue;
    private Date dateOfReturn;

    public Record(){
        book = new Book();
        user = new User();
        dateOfIssue = new Date(System.currentTimeMillis());
        dateOfReturn = new Date(System.currentTimeMillis());
    }
    public Record(Book book, User user, Date dateOfIssue, Date dateOfReturn) {
        this.book = book;
        this.user = user;
        this.dateOfIssue = dateOfIssue;
        this.dateOfReturn = dateOfReturn;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public Date getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(Date dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    @Override
    public String toString() {
        return "Record{" +
                "book=" + book +
                ", user=" + user +
                ", dateOfIssue=" + dateOfIssue +
                ", dateOfReturn=" + dateOfReturn +
                '}';
    }
}
