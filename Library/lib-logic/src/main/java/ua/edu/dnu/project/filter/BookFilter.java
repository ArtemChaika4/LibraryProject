package ua.edu.dnu.project.filter;

import ua.edu.dnu.project.model.Book;
import ua.edu.dnu.project.model.BookStatus;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BookFilter extends AbstractFilter<Book>{
    private Predicate<Book> startsWith;
    private Predicate<Book> hasStatus;
    private Predicate<Book> priceBetween;
    private Comparator<Book> sortedBy;

    public BookFilter(List<Book> list){
        super(list);
    }

    public BookFilter setHasStatus(BookStatus status){
        hasStatus = book -> book.getStatus().equals(status);
        return this;
    }
    public BookFilter setStartsWith(String value) {
        startsWith = book -> book.getTitle().startsWith(value) ||
                book.getAuthor().startsWith(value) ||
                book.getGenre().startsWith(value) ||
                String.valueOf(book.getBailPrice()).startsWith(value) ||
                String.valueOf(book.getRentalPrice()).startsWith(value);
        return this;
    }

    public BookFilter setPriceBetween(int min, int max){
        priceBetween = book ->
                book.getRentalPrice() >= min && book.getRentalPrice() <= max;
        return this;
    }
    public BookFilter setSortedByTitle(){
        sortedBy = Comparator.comparing(Book::getTitle);
        return this;
    }

    public BookFilter setSortedByAuthor(){
        sortedBy = Comparator.comparing(Book::getAuthor);
        return this;
    }

    public BookFilter setSortedByPrice(){
        sortedBy = Comparator.comparing(Book::getRentalPrice);
        return this;
    }

    public void reset(){
        hasStatus = null;
        startsWith = null;
        priceBetween = null;
        sortedBy = null;
    }

    public List<Book> select(){
        Stream<Book> bookStream = list.stream();
        bookStream = addFilter(hasStatus, bookStream);
        bookStream = addFilter(startsWith, bookStream);
        bookStream = addFilter(priceBetween, bookStream);
        if(sortedBy != null){
            bookStream = bookStream.sorted(sortedBy);
        }
        return bookStream.collect(Collectors.toList());
    }
}
