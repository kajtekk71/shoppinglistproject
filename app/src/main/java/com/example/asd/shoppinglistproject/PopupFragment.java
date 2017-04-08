package com.example.asd.shoppinglistproject;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PopupFragment extends DialogFragment {

    String title;
    ShoppingItem item;
    int position;
    DataHandler handler;
    public PopupFragment() {
        title = "Add new item";
        item = null;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setItem(ShoppingItem item){
        this.item = item;
    }
    public void setPosition(int position) {this.position = position;}
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof DataHandler){
            handler = (DataHandler) context;
        }
    }
    @Override
    public void onDetach(){
        handler = null;
        super.onDetach();
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View alertLayout = getActivity().getLayoutInflater().inflate(R.layout.fragment_popup, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle(title);
        alert.setView(alertLayout);
        final EditText titleEdit = (EditText) alertLayout.findViewById(R.id.titleEdit);
        final EditText descEdit = (EditText) alertLayout.findViewById(R.id.descEdit);
        final Button okButton = (Button) alertLayout.findViewById(R.id.acceptItem);
        final Button cancelButton = (Button) alertLayout.findViewById(R.id.cancelItem);
        okButton.setEnabled(item!=null);
        if(item!=null) {
            titleEdit.setText(item.getTitle());
            descEdit.setText(item.getDescription());
        }
        titleEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                boolean isEmpty = titleEdit.getText().toString().trim().length()==0;
                okButton.setEnabled(!isEmpty);
            }
        });

        okButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                if(item!=null) handler.replaceItem(new ShoppingItem(titleEdit.getText().toString(),descEdit.getText().toString()), position);
                else handler.addNewItem(new ShoppingItem(titleEdit.getText().toString(),descEdit.getText().toString()));
                getDialog().dismiss();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        return alert.create();
    }


}
