package ua.edu.dnu.project.service;

import ua.edu.dnu.project.db.DBSet;
import ua.edu.dnu.project.db.LibraryDB;
import ua.edu.dnu.project.model.Record;

import java.util.List;

public class RecordService implements Service<Record> {
    private final DBSet<Record> records;

    public RecordService(){
        records = LibraryDB.getInstance().getRecords();
    }

    @Override
    public void create(Record item) {
        records.add(item);
    }

    @Override
    public List<Record> getAll() {
        return records.getData();
    }

    @Override
    public Record getById(int id) {
        Record record = records.find(id);
        if(record == null){
            throw new IllegalArgumentException();
        }
        return record;
    }

    @Override
    public void update(Record item) {
        Record record = getById(item.getId());
        record.setBook(item.getBook());
        record.setUser(item.getUser());
        record.setDateOfIssue(item.getDateOfIssue());
        record.setDateOfReturn(item.getDateOfReturn());
    }

    @Override
    public void delete(int id) {
        Record record = getById(id);
        records.remove(record);
    }
}
