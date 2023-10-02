package ua.edu.dnu.project.db;

import java.util.List;

public class DBSet<E extends DBEntry> {
    private final List<E> data;
    private int idCounter;

    public DBSet(List<E> data, int idCounter){
        this.data = data;
        this.idCounter = idCounter;
    }

    public void add(E element){
        element.setId(++idCounter);
        data.add(element);
    }

    public void remove(E element){
        data.remove(element);
    }

    public E find(int id){
        for (E element : data) {
            if(element.getId() == id){
                return element;
            }
        }
        return null;
    }

    public List<E> getData(){
        return data;
    }

    public int getIdCounter() {
        return idCounter;
    }
}
