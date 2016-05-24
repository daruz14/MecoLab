package com.example.david.entrega3final;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by david on 26/04/16.
 */
public class ItemSelectedFragment extends Fragment{
    TextView mtext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_selected, container, false);
        mtext=(TextView)view.findViewById(R.id.txt_view);
        mtext.setText("");
        return view;
    }

    public void UpdateInfo(String texto ){
        mtext.setText(texto);
    }

}
