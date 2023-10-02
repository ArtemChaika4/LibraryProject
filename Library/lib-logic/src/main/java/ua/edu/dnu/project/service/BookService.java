package ua.edu.dnu.project.service;

import ua.edu.dnu.project.db.DBSet;
import ua.edu.dnu.project.db.LibraryDB;
import ua.edu.dnu.project.model.Book;

import java.util.List;

public class BookService implements Service<Book> {
    private final DBSet<Book> books;

    public BookService(){
        books = LibraryDB.getInstance().getBooks();
    }
    @Override
    public void create(Book item) {
        books.add(item);
    }

    @Override
    public List<Book> getAll() {
        return books.getData();
    }

    @Override
    public Book getById(int id) {
        Book book = books.find(id);
        if(book == null) {
            throw new IllegalArgumentException();
        }
        return book;
    }

    @Override
    public void update(Book item) {
        Book book = getById(item.getId());
        book.setTitle(item.getTitle());
        book.setAuthor(item.getAuthor());
        book.setGenre(item.getGenre());
        book.setBailPrice(item.getBailPrice());
        book.setRentalPrice(item.getRentalPrice());
    }

    @Override
    public void delete(int id) {
        Book book = getById(id);
        books.remove(book);
    }

}
