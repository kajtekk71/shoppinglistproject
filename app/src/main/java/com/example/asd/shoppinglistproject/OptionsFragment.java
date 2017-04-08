package com.example.asd.shoppinglistproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceDialogFragmentCompat;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class OptionsFragment extends PreferenceFragmentCompat {


    public OptionsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onDisplayPreferenceDialog(Preference preference) {
        PreferenceDialogFragmentCompat fragment;
        if (preference instanceof NumberPickerPreference) {
            fragment = NumberPickerPreferenceFragment.newInstance(preference);
            fragment.setTargetFragment(this, 0);
            fragment.show(getFragmentManager(),
                    "android.support.v7.preference.PreferenceFragment.DIALOG");
        }else if(preference instanceof  ColorPickerPreference){
            fragment = ColorPickerPreferenceFragment.newInstance(preference);
            fragment.setTargetFragment(this,0);
            fragment.show(getFragmentManager(), "android.support.v7.preference.PreferenceFragment.DIALOG");
        }
        else super.onDisplayPreferenceDialog(preference);
    }

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preference_fragment_layout);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        if(view!=null)
            // to chyba słaby workaround
            view.setBackgroundColor(getResources().getColor(R.color.white));
        return view;
    }
}
