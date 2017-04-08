package com.example.asd.shoppinglistproject;

/**
 * Created by asd on 04.04.2017.
 */

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class ShoppingAdapter extends RecyclerView.Adapter<ShoppingViewholder> {
    private List<ShoppingItem> list;
    private Context mContext;

    public ShoppingAdapter(List<ShoppingItem> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public ShoppingViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_shopping_item, null);
        ShoppingViewholder shoppingViewHolder = new ShoppingViewholder(view,mContext);
        return shoppingViewHolder;
    }

    @Override
    public void onBindViewHolder(final ShoppingViewholder holder, final int position) {
        final ShoppingItem shoppingItem = list.get(position);
        holder.mTitleView.setText(shoppingItem.getTitle());
        holder.mDescriptionView.setText(shoppingItem.getDescription());
        holder.changeTextSize();
        holder.mOptions.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                PopupMenu popupMenu = new PopupMenu(mContext, holder.mOptions);
                popupMenu.inflate(R.menu.more_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch(item.getItemId()){
                            case R.id.edit_item:
                                ShoppingItem sItem = list.get(position);
                                PopupFragment popupFragment = new PopupFragment();
                                popupFragment.setItem(sItem);
                                popupFragment.setTitle("Edit item");
                                popupFragment.setPosition(position);
                                popupFragment.show(((AppCompatActivity)mContext).getSupportFragmentManager(),"tag");
                                break;
                            case R.id.delete_item:
                                list.remove(position);
                                ShoppingAdapter.this.notifyDataSetChanged();
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return (list != null) ? list.size() : 0;
    }
}
