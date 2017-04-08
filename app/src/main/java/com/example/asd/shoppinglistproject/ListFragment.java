package com.example.asd.shoppinglistproject;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class ListFragment extends Fragment{

    RecyclerView recyclerView;
    ArrayList<ShoppingItem> list;
    ShoppingAdapter shoppingAdapter;
    final String filename = "shoppingList";
    public ListFragment() {
        // Required empty public constructor
    }
    public void addItemToList(ShoppingItem item)
    {
        list.add(item);
        shoppingAdapter.notifyDataSetChanged();
    }
    public void replaceItemOnList(ShoppingItem item, int position)
    {
        list.set(position, item);
        shoppingAdapter.notifyItemChanged(position);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.main_menu, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main, new OptionsFragment()).addToBackStack(null).commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setHasOptionsMenu(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_list);
        list = readItemsFromFile();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),(new LinearLayoutManager(getActivity())).getOrientation()));
        shoppingAdapter = new ShoppingAdapter(list, getActivity());
        recyclerView.setAdapter(shoppingAdapter);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.add_button);
        fab.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ShowPopUp("New item",  null);
            }
        });
        return view;
    }
    @Override
    public void onPause(){
        super.onPause();
        saveItemsToFile();
    }
    public ArrayList<ShoppingItem> readItemsFromFile(){
        ArrayList<ShoppingItem> shoppingItems = new ArrayList<>();
        FileInputStream fis;
        ObjectInputStream in;
        try {
            fis = getContext().openFileInput(filename);
            in = new ObjectInputStream(fis);
            while(true) {
                ShoppingItem si = (ShoppingItem)in.readObject();
                if(si!=null) shoppingItems.add(si);
            }
        } catch (Exception ex) {
            return shoppingItems;
        }
    }
    public void saveItemsToFile(){
        FileOutputStream fos;
        ObjectOutputStream out;
        try {
            fos = getContext().openFileOutput(filename, Context.MODE_PRIVATE);
            out = new ObjectOutputStream(fos);
            for(Serializable item: list)
                out.writeObject(item);
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void ShowPopUp(String title, ShoppingItem item) {
        PopupFragment popupFragment = new PopupFragment();
        popupFragment.setTitle(title);
        popupFragment.setItem(item);
        popupFragment.show(getActivity().getSupportFragmentManager(),"popup");
    }
}
