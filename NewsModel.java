package com.example.worldskills;

import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

public class NewsModel implements Serializable {

    protected int ID;
    protected String Name;
    protected String Description;
    protected int Price;
    protected Drawable Image;

    public Drawable getImage() {
        return Image;
    }

    public void setImage(Drawable image) {
        Image = image;
    }

    public NewsModel(int id, String name, String description, int price, Drawable image) throws MalformedURLException {
        ID = id;
        Name = name;
        Description = description;
        Price = price;
        Image = image;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
