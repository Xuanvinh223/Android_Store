package com.example.store.model;
import com.orm.SugarRecord;

public class User extends SugarRecord {
    Long id;
    String name;
    int age;

    // Default constructor is necessary for Sugar ORM
    public User() {
    }

    public User(int age,String name ) {
        this.name = name;
        this.age = age;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
