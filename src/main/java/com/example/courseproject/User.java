package com.example.courseproject;

public class User {
    private Integer id;
    private String login;
    private String hash_password;
    private String name;
    private String surname;
    private String patronymic;
    private Integer access;

    public User(Integer id, String name, String surname, String patronymic, String login, String hash_password) {
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.hash_password = hash_password;
    }

    public User() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHash_password() {
        return hash_password;
    }

    public void setHash_password(String hash_password) {

        this.hash_password = hash_password;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Integer getAccess() {
        return access;
    }

    public void setAccess(Integer access) {
        this.access = access;
    }


}
