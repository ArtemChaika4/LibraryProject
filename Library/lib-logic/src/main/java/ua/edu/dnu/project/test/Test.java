package ua.edu.dnu.project.test;

import ua.edu.dnu.project.filter.BookFilter;
import ua.edu.dnu.project.model.Book;
import ua.edu.dnu.project.model.BookStatus;
import java.util.ArrayList;
import java.util.List;


public class Test {
    public static void main(String[] args) {
        BookFilter filter = new BookFilter(generate(10));
        System.out.println(filter.select());
        System.out.println(filter.setHasStatus(BookStatus.AVAILABLE).select());
        filter.setStartsWith("Title").setSortedByPrice();
        System.out.println(filter.select());
    }

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
