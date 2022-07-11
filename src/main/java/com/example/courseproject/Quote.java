package com.example.courseproject;

public class Quote {
    private Integer id;
    private String quote;
    private String teacher;
    private String subject;
    private String data;
    private Integer id_user;
    private Integer access;

    public Quote( Integer id, String quote, String teacher, String subject, String data, Integer id_user) {
        this.id = id;
        this.quote = quote;
        this.teacher = teacher;
        this.subject = subject;
        this.data = data;
        this.id_user = id_user;
        //this.access = access;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Integer getAccess() {
        return access;
    }

    public void setAccess(Integer access) {
        this.access = access;
    }
}
