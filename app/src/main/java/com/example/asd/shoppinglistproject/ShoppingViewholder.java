package com.example.asd.shoppinglistproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
/**
 * Created by asd on 04.04.2017.
 */

public class ShoppingViewholder extends RecyclerView.ViewHolder {
    public TextView mTitleView;
    public TextView mDescriptionView;
    public ImageButton mOptions;
    public Context mContext;
    private int[] rainbow;
    public ShoppingViewholder(View itemView, Context context) {
        super(itemView);
        mContext = context;
        rainbow = context.getResources().getIntArray(R.array.rainbow);
        this.mTitleView = (TextView) itemView.findViewById(R.id.item_title);
        this.mDescriptionView = (TextView) itemView.findViewById(R.id.item_description);
        this.mOptions = (ImageButton) itemView.findViewById(R.id.item_more);

    }
    public void changeTextSize(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        mTitleView.setTextSize(sharedPreferences.getInt("text_size",24));
        mTitleView.setTextColor(rainbow[sharedPreferences.getInt("font_color",0)]);
        mDescriptionView.setTextSize(sharedPreferences.getInt("text_size",24));
        mDescriptionView.setTextColor(rainbow[sharedPreferences.getInt("font_color",0)]);
    }
}