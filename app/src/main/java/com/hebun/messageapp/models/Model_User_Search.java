package com.hebun.messageapp.models;

public class Model_User_Search {
    String user_id, username;

    public Model_User_Search() {
    }

    public Model_User_Search(String user_id, String username) {
        this.user_id = user_id;
        this.username = username;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
