package ua.edu.dnu.project.model;
import ua.edu.dnu.project.db.DBEntry;

public class Book extends DBEntry {
    private String title;
    private String author;
    private String genre;
    private int bailPrice;
    private int rentalPrice;

    public Book(){
        title = "";
        author = "";
        genre = "";
        bailPrice = 0;
        rentalPrice = 0;
    }
    public Book(String title, String author, String genre, int bailPrice, int rentalPrice) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bailPrice = bailPrice;
        this.rentalPrice = rentalPrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getBailPrice() {
        return bailPrice;
    }

    public void setBailPrice(int bailPrice) {
        this.bailPrice = bailPrice;
    }

    public int getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(int rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", bailPrice=" + bailPrice +
                ", rentalPrice=" + rentalPrice +
                '}';
    }
}
