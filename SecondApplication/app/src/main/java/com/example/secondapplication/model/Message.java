package com.example.secondapplication.model;

public class Message {
    private int img;
    private String name, title, time;

    public Message() {
    }

    public Message(int img, String name, String title, String time) {
        this.img = img;
        this.name = name;
        this.title = title;
        this.time = time;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
