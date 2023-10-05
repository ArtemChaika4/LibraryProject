package ua.edu.dnu.project.service;

import ua.edu.dnu.project.exception.ServiceException;

import java.util.List;

public interface Service<T> {
    void create(T item) throws ServiceException;
    List<T> getAll();
    T getById(int id) throws ServiceException;
    void update(T item) throws ServiceException;
    void delete(int id) throws ServiceException;
}
