package ua.edu.dnu.project.model;

import ua.edu.dnu.project.db.DBEntry;

public class User extends DBEntry {
    private String lastname;
    private String name;
    private String patronymic;
    private String address;
    private String phone;

    public User(){
        lastname = "";
        name = "";
        patronymic = "";
        address = "";
        phone = "";
    }
    public User(String lastname, String name, String patronymic, String address, String phone) {
        this.lastname = lastname;
        this.name = name;
        this.patronymic = patronymic;
        this.address = address;
        this.phone = phone;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + getId() + '\'' +
                ", lastname='" + lastname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
