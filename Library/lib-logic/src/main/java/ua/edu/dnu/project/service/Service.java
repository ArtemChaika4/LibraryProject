package ua.edu.dnu.project.service;

import java.util.List;

public interface Service<T> {
    void create(T item);
    List<T> getAll();
    T getById(int id);
    void update(T item);
    void delete(int id);
}
