package com.example.david.entrega4;

import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by david on 10/05/16.
 */
public class MyListFragment extends android.support.v4.app.ListFragment{
    private static final String KEY_arreglo = "arreglo";
    ArrayList<String> arreglofinal;
    MyAdapter adapter;
    OnFragmentSendText mCallBack;
    DBHelper mydb;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        mydb = new DBHelper(getActivity());
        if(savedInstanceState != null){
            arreglofinal=savedInstanceState.getStringArrayList(KEY_arreglo);
        }
        else {
            //String[] Arreglo = {"A","Andres","Alex","Martin","R","Rene","D","Diego","P","Pablo","I","Ignacio","M","Miguelo","S","Sebastian","David","N","Nicolas","C","Carlos","J","Juan","T","Tomas"};
            ArrayList<String> Arreglo = mydb.getAllCotacts();
            Collections.sort(Arreglo);
             arreglofinal = Arreglo;
            //Arrays.sort(Arreglo);
            //arreglofinal = new ArrayList<String>(Arrays.asList(Arreglo));
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
        mydb.insertContact(item);
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
