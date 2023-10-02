package ua.edu.dnu.project.test;

import ua.edu.dnu.project.db.LibraryDB;
import ua.edu.dnu.project.model.Book;
import ua.edu.dnu.project.model.Record;
import ua.edu.dnu.project.model.User;

import java.sql.Date;

public class Test {
    public static void main(String[] args) {
        LibraryDB libraryDB = LibraryDB.getInstance();
        libraryDB.getBooks().add(
                new Book("name", "author", "fantasy", 100, 100)
        );
        libraryDB.getUsers().add(
                new User("Name", "Lastname", "Patronymic", "Street","06600000")
        );
        libraryDB.getRecords().add(
                new Record(
                        libraryDB.getBooks().getData().get(0),
                        libraryDB.getUsers().getData().get(0),
                        new Date(System.currentTimeMillis()),
                        new Date(System.currentTimeMillis() + 1000000)
                )
        );
        libraryDB.save();
    }
}
