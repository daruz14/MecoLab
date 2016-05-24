package com.example.david.entrega3;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by david on 20/04/16.
 */
public class Item_selected extends Fragment {
    TextView mtext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_selected, container, false);
        mtext=(TextView)view.findViewById(R.id.txt_secondA);
        mtext.setText("");
        return view;
    }

    public void Update_info(String txt){
        mtext.setText(txt);
    }
}
