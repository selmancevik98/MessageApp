package com.hebun.messageapp.models;

public class Model_Chats {
    String user_id, sohbet_id, name, surname, username;

    public Model_Chats() {
    }

    public Model_Chats(String user_id, String sohbet_id, String name, String surname, String username) {
        this.user_id = user_id;
        this.sohbet_id = sohbet_id;
        this.name = name;
        this.surname = surname;
        this.username = username;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSohbet_id() {
        return sohbet_id;
    }

    public void setSohbet_id(String sohbet_id) {
        this.sohbet_id = sohbet_id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
