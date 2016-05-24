package com.example.david.entrega3final;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by david on 26/04/16.
 */
public class MyListFragment extends ListFragment{
    private static final String KEY_arreglo = "arreglo";
    ArrayList<String> arreglofinal;
    MyAdapter adapter;
    OnFragmentSendText mCallBack;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState != null){
            arreglofinal=savedInstanceState.getStringArrayList(KEY_arreglo);
        }
        else {
            String[] Arreglo = {"A","Andres","Alex","Martin","R","Rene","D","Diego","P","Pablo","I","Ignacio","M","Miguelo","S","Sebastian","David","N","Nicolas","C","Carlos","J","Juan","T","Tomas"};
            Arrays.sort(Arreglo);
            arreglofinal = new ArrayList<String>(Arrays.asList(Arreglo));
        }

        adapter= new MyAdapter(getActivity(),arreglofinal);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

    public void AgregarItem(String item){
        arreglofinal.add(item);
        String indice = Character.toString(item.charAt(0));

        if (!arreglofinal.contains(indice)){arreglofinal.add(indice);}
        Collections.sort(arreglofinal);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList(KEY_arreglo,arreglofinal);
    }
}