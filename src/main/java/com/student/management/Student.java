package com.student.management;

public class Student {
    private int id;
    private String name;
    private String email;
    private String course; // 🔹 Changed from age to course

    // Constructor
    public Student() {}

    public Student(int id, String name, String email, String course) { // 🔹 Updated
        this.id = id;
        this.name = name;
        this.email = email;
        this.course = course;
    }

    // Getters and Setters
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse() { // 🔹 Added getter for course
        return course;
    }

    public void setCourse(String course) { // 🔹 Added setter for course
        this.course = course;
    }
}
