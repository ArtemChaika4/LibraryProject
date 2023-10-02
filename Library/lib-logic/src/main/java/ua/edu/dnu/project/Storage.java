package ua.edu.dnu.project;

import ua.edu.dnu.project.db.LibraryDB;
import ua.edu.dnu.project.service.BookService;
import ua.edu.dnu.project.service.RecordService;
import ua.edu.dnu.project.service.UserService;

public class Storage {
    private BookService bookService;
    private UserService userService;
    private RecordService recordService;

    public BookService books(){
        if(bookService == null){
            bookService = new BookService();
        }
        return bookService;
    }

    public UserService users(){
        if(userService == null){
            userService = new UserService();
        }
        return userService;
    }

    public RecordService records(){
        if(recordService == null){
            recordService = new RecordService();
        }
        return recordService;
    }

    public void save(){
        LibraryDB.getInstance().save();
    }
}
