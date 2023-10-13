package ua.edu.dnu.project.filter;

import ua.edu.dnu.project.model.User;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserFilter extends AbstractFilter<User> {
    private Predicate<User> startsWith;

    private Predicate<User> contains;

    private Comparator<User> sortedBy;

    public UserFilter(List<User> list) {
        super(list);
    }

    public UserFilter setStartsWith(String value){
        startsWith = user -> user.getLastname().startsWith(value) ||
                user.getName().startsWith(value) ||
                user.getPatronymic().startsWith(value) ||
                user.getAddress().startsWith(value) ||
                user.getPhone().startsWith(value);
        return this;
    }

    public UserFilter setContains(String value) {
        contains = user -> user.getLastname().contains(value) ||
                user.getName().contains(value) ||
                user.getPatronymic().contains(value) ||
                user.getAddress().contains(value) ||
                user.getPhone().contains(value);
        return this;
    }

    public UserFilter setSortedByLastname(){
        sortedBy = Comparator.comparing(User::getLastname);
        return this;
    }

    public UserFilter setSortedByName(){
        sortedBy = Comparator.comparing(User::getName);
        return this;
    }

    public UserFilter setSortedByAddress(){
        sortedBy = Comparator.comparing(User::getAddress);
        return this;
    }

    public UserFilter setSortedByPatronymic(){
        sortedBy = Comparator.comparing(User::getPatronymic);
        return this;
    }

    public void reset(){
        startsWith = null;
        contains = null;
        sortedBy = null;
    }

    public List<User> select(){
        Stream<User> userStream = list.stream();
        userStream = addFilter(startsWith, userStream);
        userStream = addFilter(contains, userStream);
        if(sortedBy != null){
            userStream = userStream.sorted(sortedBy);
        }
        return userStream.collect(Collectors.toList());
    }
}
