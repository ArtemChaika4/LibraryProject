package ua.edu.dnu.project.service;

import ua.edu.dnu.project.db.DBSet;
import ua.edu.dnu.project.db.LibraryDB;
import ua.edu.dnu.project.exception.ServiceException;
import ua.edu.dnu.project.model.*;

import java.util.List;

public class RecordService implements Service<Record> {
    private final DBSet<Record> records;

    public RecordService(){
        records = LibraryDB.getInstance().getRecords();
    }

    private void validateRecord(Record record) throws ServiceException {
        Book book = new BookService().getById(record.getBook().getId());
        User user = new UserService().getById(record.getUser().getId());
        if (book.getStatus() == BookStatus.DELETED){
            throw new ServiceException("Cannot rent deleted book");
        }
    }

    //throws ServiceException
    @Override
    public void create(Record item) throws ServiceException {
        validateRecord(item);
        item.setStatus(RecordStatus.RENTED);
        item.getBook().setStatus(BookStatus.BORROWED);
        records.add(item);
    }

    @Override
    public List<Record> getAll() {
        return records.getData();
    }

    //throws ServiceException
    @Override
    public Record getById(int id) throws ServiceException {
        Record record = records.find(id);
        if(record == null){
            throw new ServiceException("Record not found, id: " + id);
        }
        return record;
    }

    //throws ServiceException
    @Override
    public void update(Record item) throws ServiceException {
        validateRecord(item);
        Record record = getById(item.getId());
        record.setBook(item.getBook());
        record.setUser(item.getUser());
        record.setDateOfIssue(item.getDateOfIssue());
        record.setDateOfReturn(item.getDateOfReturn());
    }

    //throws ServiceException
    @Override
    public void delete(int id) throws ServiceException {
        Record record = getById(id);
        records.remove(record);
    }

    public void deleteUserRecords(int userId){
        records.getData().removeIf(record -> record.getUser().getId() == userId);
    }
}
