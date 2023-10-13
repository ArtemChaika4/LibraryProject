package ua.edu.dnu.project.filter;

import ua.edu.dnu.project.model.Record;
import ua.edu.dnu.project.model.RecordStatus;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RecordFilter extends AbstractFilter<Record>{
    private Predicate<Record> hasStatus;
    private Predicate<Record> contains;
    private Comparator<Record> sortedBy;

    public RecordFilter(List<Record> list)
    {
        super(list);
    }

    public RecordFilter setHasStatus(RecordStatus status){
        hasStatus = record -> record.getStatus().equals(status);
        return this;
    }

    public RecordFilter setContains(String value) {
        contains = record -> record.getBook().getTitle().contains(value) ||
                record.getUser().getLastname().contains(value) ||
                record.getUser().getName().contains(value) ||
                String.valueOf(record.getDateOfIssue()).contains(value) ||
                String.valueOf(record.getDateOfReturn()).contains(value);
        return this;
    }

    public RecordFilter setSortedByTitle(){
        sortedBy = Comparator.comparing(record -> record.getBook().getTitle());
        return this;
    }

    public RecordFilter setSortedByLastname(){
        sortedBy = Comparator.comparing(record -> record.getUser().getLastname());
        return this;
    }

    public RecordFilter setSortedByDateOfIssue(){
        sortedBy = Comparator.comparing(Record::getDateOfIssue);
        return this;
    }

    public RecordFilter setSortedByDateOfReturn(){
        sortedBy = Comparator.comparing(Record::getDateOfReturn);
        return this;
    }

    public RecordFilter setSortedByStatus(){
        sortedBy = Comparator.comparing(Record::getStatus);
        return this;
    }

    public void reset(){
        hasStatus = null;
        contains = null;
        sortedBy = null;
    }

    public List<Record> select(){
        Stream<Record> bookStream = list.stream();
        bookStream = addFilter(hasStatus, bookStream);
        bookStream = addFilter(contains, bookStream);
        if(sortedBy != null){
            bookStream = bookStream.sorted(sortedBy);
        }
        return bookStream.collect(Collectors.toList());
    }
}
