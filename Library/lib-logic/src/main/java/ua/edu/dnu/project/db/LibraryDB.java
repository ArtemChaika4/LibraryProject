package ua.edu.dnu.project.db;

import com.google.gson.reflect.TypeToken;
import ua.edu.dnu.project.exception.ServiceException;
import ua.edu.dnu.project.model.Book;
import ua.edu.dnu.project.model.Record;
import ua.edu.dnu.project.model.User;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class LibraryDB {
    private DBSet<Book> books;
    private DBSet<User> users;
    private DBSet<Record> records;
    private static LibraryDB instance;
    private LibraryDB(){}

    public void load() throws FileNotFoundException, ServiceException {
        Properties properties = DBUtils.getPropertiesFromResource("db/db.properties");
        List<Book> bookList =
                DBUtils.readJson(properties.getProperty("books"), new TypeToken<List<Book>>(){}.getType());
        List<User> userList =
                DBUtils.readJson(properties.getProperty("users"), new TypeToken<List<User>>(){}.getType());
        List<Record> recordList =
                DBUtils.readJson(properties.getProperty("records"), new TypeToken<List<Record>>(){}.getType());
        List<Integer> idCounters =
                DBUtils.readJson(properties.getProperty("id-counters"), new TypeToken<List<Integer>>(){}.getType());

        books = new DBSet<>(bookList, idCounters.get(0));
        users = new DBSet<>(userList, idCounters.get(1));
        for (Record record : recordList) {
            int bookId = record.getBook().getId();
            int userId = record.getUser().getId();
            record.setBook(books.find(bookId));
            record.setUser(users.find(userId));
        }
        records = new DBSet<>(recordList, idCounters.get(2));
    }

    public void save() throws FileNotFoundException {
        Properties properties = DBUtils.getPropertiesFromResource("db/db.properties");
        DBUtils.writeJson(properties.getProperty("books"), books.getData());
        DBUtils.writeJson(properties.getProperty("users"), users.getData());
        DBUtils.writeJson(properties.getProperty("records"), records.getData());
        List<Integer> idCounters = List.of(books.getIdCounter(), users.getIdCounter(), records.getIdCounter());
        DBUtils.writeJson(properties.getProperty("id-counters"), idCounters);
    }

    public DBSet<Book> getBooks() {
        return books;
    }

    public DBSet<User> getUsers() {
        return users;
    }

    public DBSet<Record> getRecords() {
        return records;
    }

    public static LibraryDB getInstance()  {
        if(instance == null){
            instance = new LibraryDB();
        }
        return instance;
    }
}