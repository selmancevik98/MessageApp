package com.hebun.messageapp.messages;

public class MessageModel {
    String from, text, date, time;

    public MessageModel() {
    }

    public MessageModel(String from, String text, String date, String time) {
        this.from = from;
        this.text = text;
        this.date = date;
        this.time = time;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
