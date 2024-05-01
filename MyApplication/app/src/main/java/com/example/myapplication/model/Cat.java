package com.example.myapplication.model;

public class Cat {
    private int img;
    private String name, desc;
    private double price;

    public Cat(int img, String name, String desc, double price) {
        this.img = img;
        this.name = name;
        this.desc = desc;
        this.price = price;
    }

    public Cat(int img, String name) {
        this.img = img;
        this.name = name;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
