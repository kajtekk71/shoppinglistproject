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

public class NumberPickerPreferenceFragment extends PreferenceDialogFragmentCompat {
    protected NumberPicker mPicker;
    private Integer mNumber = 0;
    @Override
    protected View onCreateDialogView(Context context) {
        super.onCreateDialogView(context);
        mPicker = new NumberPicker(getContext());
        return mPicker;
    }
    @Override protected void onBindDialogView(View v){
        super.onBindDialogView(v);
        mPicker.setMinValue(1);
        mPicker.setMaxValue(50);
        NumberPickerPreference numberPickerPreference = (NumberPickerPreference) getPreference();
        mPicker.setValue(numberPickerPreference.mNumber);
    }
    @Override
    public void onDialogClosed(boolean positiveResult) {
        if (positiveResult) {
            NumberPickerPreference numberPickerPreference = (NumberPickerPreference) getPreference();
            numberPickerPreference.setValue(mPicker.getValue());
        }
    }
    public static NumberPickerPreferenceFragment newInstance(Preference preference) {
        NumberPickerPreferenceFragment fragment = new NumberPickerPreferenceFragment();
        Bundle bundle = new Bundle(1);
        bundle.putString("key", preference.getKey());
        fragment.setArguments(bundle);
        return fragment;
    }
}
