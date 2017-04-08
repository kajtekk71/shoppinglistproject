package com.example.asd.shoppinglistproject;

import java.io.Serializable;

/**
 * Created by asd on 28.03.2017.
 */

public class ShoppingItem implements Serializable {
    private String title;
    private String description;

    public ShoppingItem(String title, String description)
    {
        this.title=title;
        this.description=description;
    }
    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
}
