package ua.edu.dnu.project.filter;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public abstract class AbstractFilter<T> {
    protected List<T> list;
    public AbstractFilter(List<T> list){
        this.list = list;
    }
    protected Stream<T> addFilter(Predicate<T> filter, Stream<T> stream){
        if(filter != null){
            stream = stream.filter(filter);
        }
        return stream;
    }

    public abstract void reset();
    public abstract List<T> select();
}
