package com.example.gamecardinal.model;

import com.google.gson.annotations.Expose;

public class User {
    @Expose(serialize = false)
    private int user_id;

    private String name;

    private String password;

    public User() {}

    public int getId() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int user_id) {
        this.user_id = user_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
