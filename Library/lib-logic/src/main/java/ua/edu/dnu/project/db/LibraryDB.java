package ua.edu.dnu.project.db;

import com.google.gson.reflect.TypeToken;
import ua.edu.dnu.project.model.Book;
import ua.edu.dnu.project.model.Record;
import ua.edu.dnu.project.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class LibraryDB {
    private final Properties properties;
    private DBSet<Book> books;
    private DBSet<User> users;
    private DBSet<Record> records;
    private static LibraryDB instance;
    private LibraryDB(){
        properties = DBUtils.getPropertiesFromResource("db/db.properties");
        load();
    }

    public void load(){
        List<Book> bookList = new ArrayList<>();
        DBUtils.readJson(properties.getProperty("books"), bookList, new TypeToken<List<Book>>(){}.getType());
        List<User> userList = new ArrayList<>();
        DBUtils.readJson(properties.getProperty("users"), userList, new TypeToken<List<User>>(){}.getType());
        List<Record> recordList = new ArrayList<>();
        DBUtils.readJson(properties.getProperty("records"), recordList, new TypeToken<List<Record>>(){}.getType());
        List<Integer> idCounters = new ArrayList<>();
        DBUtils.readJson(properties.getProperty("id-counters"), idCounters, new TypeToken<List<Integer>>(){}.getType());

        books = new DBSet<>(bookList, idCounters.get(0));
        users = new DBSet<>(userList, idCounters.get(1));
        records = new DBSet<>(recordList, idCounters.get(2));
    }

    public void save(){
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

    public static LibraryDB getInstance() {
        if(instance == null){
            instance = new LibraryDB();
        }
        return instance;
    }
}