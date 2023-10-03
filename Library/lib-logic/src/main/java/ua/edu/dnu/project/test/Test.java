package ua.edu.dnu.project.test;

import ua.edu.dnu.project.db.LibraryDB;
import ua.edu.dnu.project.model.Book;
import ua.edu.dnu.project.model.Record;
import ua.edu.dnu.project.model.User;

import java.sql.Date;

public class Test {
    public static void main(String[] args) {
        LibraryDB libraryDB = LibraryDB.getInstance();
        System.out.println(libraryDB.getBooks().getData());
        System.out.println(libraryDB.getUsers().getData());
        System.out.println(libraryDB.getRecords().getData());
    }
}
