package com.example.asd.shoppinglistproject;

/**
 * Created by asd on 06.04.2017.
 */

public interface DataHandler {
    void addNewItem(ShoppingItem item);
    void replaceItem(ShoppingItem item, int position);
}
