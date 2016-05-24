package com.example.david.entrega3;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

import static android.widget.AdapterView.*;

/**
 * Created by david on 19/04/16.
 */
public class ListItem extends ListFragment {

    ArrayList arreglofinal;
    MyAdapter adapter;
    OnFragmentSendText mCallBack;


    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        String[] Arreglo = {"A","Andres","Alex","Martin","R","Rene","D","Diego","P","Pablo","I","Ignacio","M","Miguelo","S","Sebastian","David","N","Nicolas","C","Carlos","J","Juan","T","Tomas"};
        Arrays.sort(Arreglo);
        arreglofinal = new ArrayList<String>(Arrays.asList(Arreglo));
        adapter= new MyAdapter(getActivity(),arreglofinal);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (arreglofinal.get(position).toString().length() == 1) {
                } else {
                    String item = arreglofinal.get(position).toString();
                    if (mCallBack != null) {
                        mCallBack.onSentText(item);
                    }
                }
            }
        });
    }

    public interface OnFragmentSendText{
        public void onSentText(String text);
    }

    public void setListener(OnFragmentSendText listener) {
        mCallBack = listener;
    }



}
