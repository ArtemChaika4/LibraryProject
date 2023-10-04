package ua.edu.dnu.project.service;

import ua.edu.dnu.project.db.DBSet;
import ua.edu.dnu.project.db.LibraryDB;
import ua.edu.dnu.project.model.Book;
import ua.edu.dnu.project.model.BookStatus;

import java.util.List;

public class BookService implements Service<Book> {
    private final DBSet<Book> books;

    public BookService(){
        books = LibraryDB.getInstance().getBooks();
    }

    private void validateBook(Book book){
        if(book.getStatus() != BookStatus.AVAILABLE){
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void create(Book item) {
        validateBook(item);
        books.add(item);
    }

    @Override
    public List<Book> getAll() {
        return books.getData();
    }

    //throws ServiceException
    @Override
    public Book getById(int id) {
        Book book = books.find(id);
        if(book == null) {
            throw new IllegalArgumentException();
        }
        return book;
    }

    //throws ServiceException
    @Override
    public void update(Book item) {
        Book book = getById(item.getId());
        book.setTitle(item.getTitle());
        book.setAuthor(item.getAuthor());
        book.setGenre(item.getGenre());
        book.setBailPrice(item.getBailPrice());
        book.setRentalPrice(item.getRentalPrice());
    }

    //throws ServiceException
    @Override
    public void delete(int id) {
        Book book = getById(id);
        book.setStatus(BookStatus.DELETED);
        //books.remove(book);
    }

}
