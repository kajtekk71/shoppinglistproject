package com.example.asd.shoppinglistproject;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceDialogFragmentCompat;
import android.view.View;
import android.widget.NumberPicker;

/**
 * Created by asd on 08.04.2017.
 */

public class ColorPickerPreferenceFragment extends PreferenceDialogFragmentCompat {
    String[] colorValues;
    NumberPicker mPicker;
    @Override
    protected View onCreateDialogView(Context context) {
        super.onCreateDialogView(context);
        colorValues = new String[] { "Black", "Pink", "Red", "Orange", "Yellow", "Chartreuse", "Green", "SpringGreen", "Cyan", "Azure", "Blue", "Violet","Magenta"};
        mPicker = new NumberPicker(context);
        return mPicker;
    }
    @Override
    public void onDialogClosed(boolean positiveResult) {
        if (positiveResult) {
            ColorPickerPreference colorPickerPreference = (ColorPickerPreference) getPreference();
            colorPickerPreference.setValue(mPicker.getValue());
        }
    }
    @Override protected void onBindDialogView(View v){
        super.onBindDialogView(v);
        ColorPickerPreference colorPickerPreference = (ColorPickerPreference) getPreference();
        mPicker.setMinValue(0);
        mPicker.setMaxValue(colorValues.length-1);
        mPicker.setDisplayedValues(colorValues);
        mPicker.setValue(colorPickerPreference.mNumber);
    }
    public static ColorPickerPreferenceFragment newInstance(Preference preference) {
        ColorPickerPreferenceFragment fragment = new ColorPickerPreferenceFragment();
        Bundle bundle = new Bundle(1);
        bundle.putString("key", preference.getKey());
        fragment.setArguments(bundle);
        return fragment;
    }
}
