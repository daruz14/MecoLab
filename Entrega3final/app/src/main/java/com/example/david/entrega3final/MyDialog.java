package com.example.david.entrega3final;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by david on 27/04/16.
 */
public class MyDialog extends DialogFragment {


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
    public interface AddItemDialogListener{
        void onFinishAddItemDialog(String inputText);
    }

}

