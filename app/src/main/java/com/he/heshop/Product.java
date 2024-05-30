package com.he.heshop;

import android.graphics.Bitmap;

public class Product {

    private int id;
    private String name;
    private String description;
    private int price;
    private Bitmap image;

    public Product(int id, String name, String description, int price, Bitmap image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
    }
    public Product( String name, String description, int price, Bitmap image) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public Bitmap getImage() {
        return image;
    }
}
