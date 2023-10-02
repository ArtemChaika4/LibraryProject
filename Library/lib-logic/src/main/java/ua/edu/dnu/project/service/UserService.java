package ua.edu.dnu.project.service;

import ua.edu.dnu.project.db.DBSet;
import ua.edu.dnu.project.db.LibraryDB;
import ua.edu.dnu.project.model.User;
import java.util.List;

public class UserService implements Service<User> {
    private final DBSet<User> users;

    public UserService(){
        users = LibraryDB.getInstance().getUsers();
    }

    @Override
    public void create(User item) {
        users.add(item);
    }

    @Override
    public List<User> getAll() {
        return users.getData();
    }

    @Override
    public User getById(int id) {
        User user = users.find(id);
        if(user == null){
            throw new IllegalArgumentException();
        }
        return user;
    }

    @Override
    public void update(User item) {
        User user = getById(item.getId());
        user.setLastname(item.getLastname());
        user.setName(item.getName());
        user.setPatronymic(item.getPatronymic());
        user.setAddress(item.getAddress());
        user.setPhone(item.getPhone());
    }

    @Override
    public void delete(int id) {
        User user = getById(id);
        users.remove(user);
    }
}
