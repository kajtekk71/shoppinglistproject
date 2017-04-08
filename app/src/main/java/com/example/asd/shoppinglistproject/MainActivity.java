package com.example.asd.shoppinglistproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

public class MainActivity extends AppCompatActivity implements DataHandler {

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        //źle ale działa
        getSupportFragmentManager().beginTransaction().replace(R.id.main, new ListFragment(), "listFragment").commit();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.main, new ListFragment(), "listFragment").commit();
    }

    @Override
    public void addNewItem(ShoppingItem item) {
        ListFragment listFragment = (ListFragment) getSupportFragmentManager().findFragmentByTag("listFragment");
        listFragment.addItemToList(item);
    }

    @Override
    public void replaceItem(ShoppingItem item, int position) {
        ListFragment listFragment = (ListFragment) getSupportFragmentManager().findFragmentByTag("listFragment");
        listFragment.replaceItemOnList(item,position);
    }
}