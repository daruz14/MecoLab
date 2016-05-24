package com.example.david.entrega2final;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by david on 13/04/16.
 */
public class MyDialog extends DialogFragment {

    public interface AddItemDialogListener{
        void onFinishAddItemDialog(String inputText);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_fragment,null);
        Button ok = (Button)view.findViewById(R.id.btn_ok);
        Button cancel = (Button)view.findViewById(R.id.btn_cancel);
        final EditText new_item = (EditText)view.findViewById(R.id.text_new_item);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String item = new_item.getText().toString();
                String item_2 = item.substring(0,1).toUpperCase() + item.substring(1);
                AddItemDialogListener activity = (AddItemDialogListener) getActivity();
                activity.onFinishAddItemDialog(item_2);
                dismiss();

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;
    }

}
