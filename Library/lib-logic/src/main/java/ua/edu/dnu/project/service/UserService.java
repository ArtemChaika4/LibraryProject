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

    private void validateUser(User user){
        getByPhone(user.getPhone());
    }

    @Override
    public void create(User item) {
        validateUser(item);
        users.add(item);
    }

    @Override
    public List<User> getAll() {
        return users.getData();
    }

    //throws ServiceException
    @Override
    public User getById(int id) {
        User user = users.find(id);
        if(user == null){
            throw new IllegalArgumentException();
        }
        return user;
    }

    public User getByPhone(String phone){
        for (User user : users.getData()) {
            if(user.getPhone().equals(phone)){
                return user;
            }
        }
        throw new IllegalArgumentException();
    }

    //throws ServiceException
    @Override
    public void update(User item) {
        User user = getById(item.getId());
        user.setLastname(item.getLastname());
        user.setName(item.getName());
        user.setPatronymic(item.getPatronymic());
        user.setAddress(item.getAddress());
        user.setPhone(item.getPhone());
    }

    //throws ServiceException
    @Override
    public void delete(int id) {
        User user = getById(id);
        users.remove(user);
        //remove all user records
    }
}
