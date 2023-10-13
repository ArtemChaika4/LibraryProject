package ua.edu.dnu.project.test;

import ua.edu.dnu.project.filter.BookFilter;
import ua.edu.dnu.project.filter.RecordFilter;
import ua.edu.dnu.project.model.Book;
import ua.edu.dnu.project.model.BookStatus;
import ua.edu.dnu.project.model.Record;
import ua.edu.dnu.project.model.RecordStatus;

import java.util.ArrayList;
import java.util.List;


public class Test {
    public static void main(String[] args) {
        List<Record> records = new ArrayList<>();
        for (int i = 1; i < 5; i++){
            Record record = new Record();
            record.setId(i);
            record.setStatus(RecordStatus.values()[i % 3]);
            records.add(record);
        }
        RecordFilter filter = new RecordFilter(records);
        System.out.println(filter.select());
        filter.setSortedByStatus();
        System.out.println(filter.select());
    }

    //generate List of n Books
    private static List<Book> generate(int n){
        List<Book> list = new ArrayList<>();
        for(int i = 1; i <= n; i++){
            list.add(new Book(
                    "Title" + i,
                    "Author" + i,
                    "Genre" + i,
                    i * 100,
                    100 - i * 10,
                    BookStatus.values()[i % 3]
                    ));
        }

        return list;
    }
}
