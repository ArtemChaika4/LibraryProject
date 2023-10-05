package ua.edu.dnu.project.service;

import ua.edu.dnu.project.db.DBSet;
import ua.edu.dnu.project.db.LibraryDB;
import ua.edu.dnu.project.model.*;

import java.util.List;

public class RecordService implements Service<Record> {
    private final DBSet<Record> records;

    public RecordService(){
        records = LibraryDB.getInstance().getRecords();
    }

    private void validateRecord(Record record){
        Book book = new BookService().getById(record.getBook().getId());
        User user = new UserService().getById(record.getUser().getId());
        if (book.getStatus() == BookStatus.DELETED){
            throw new IllegalArgumentException();
        }
    }

    //throws ServiceException
    @Override
    public void create(Record item) {
        validateRecord(item);
        item.setStatus(RecordStatus.RENTED);
        item.getBook().setStatus(BookStatus.MISSING);
        records.add(item);
    }

    @Override
    public List<Record> getAll() {
        return records.getData();
    }

    //throws ServiceException
    @Override
    public Record getById(int id) {
        Record record = records.find(id);
        if(record == null){
            throw new IllegalArgumentException();
        }
        return record;
    }

    //throws ServiceException
    @Override
    public void update(Record item) {
        validateRecord(item);
        Record record = getById(item.getId());
        record.setBook(item.getBook());
        record.setUser(item.getUser());
        record.setDateOfIssue(item.getDateOfIssue());
        record.setDateOfReturn(item.getDateOfReturn());
    }

    //throws ServiceException
    @Override
    public void delete(int id) {
        Record record = getById(id);
        records.remove(record);
    }

    public void deleteUserRecords(int userId){
        for (Record record : records.getData()) {
            if(record.getUser().getId() == userId){
                records.remove(record);
            }
        }
    }
}
