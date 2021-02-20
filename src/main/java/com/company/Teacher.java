package com.company;

import java.io.Serializable;

public class Teacher implements Serializable {
    private int id;
    private String name;
    private String last_name;
    private String faculty;
    private String subject;

    public Teacher(int id, String name, String last_name, String faculty, String subject){
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.faculty = faculty;
        this.subject = subject;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }



}
