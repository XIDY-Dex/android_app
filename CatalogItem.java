package com.example.worldskills;

import java.io.Serializable;
import java.util.Formatter;

public class CatalogItem implements Serializable {
    protected Integer ID;
    protected String Name;
    protected String Description;
    protected Integer Price;
    protected String Category;
    protected String TimeResult;
    protected String Preparation;
    protected String Bio;

    public CatalogItem(int id, String name, String description, int price, String category, String timeResult, String preparation, String bio) {
        this.ID = id;
        this.Name = name;
        this.Description = description;
        this.Price = price;
        this.Category = category;
        this.TimeResult = timeResult;
        this.Preparation = preparation;
        this.Bio = bio;
    }
}
