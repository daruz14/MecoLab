package com.example.david.entrega51;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import com.example.david.entrega51.UsuariosProvider.Usuarios;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by david on 11/05/16.
 */
public class MyListFragment extends android.support.v4.app.ListFragment{
    private static final String KEY_arreglo = "arreglo";
    ArrayList<String> arreglofinal;
    MyAdapter adapter;
    OnFragmentSendText mCallBack;
    UsuariosSqliteHelper mydb;


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

            ArrayList<String> Arreglo = getAllCotacts();
            Collections.sort(Arreglo);
            arreglofinal = Arreglo;
            //String[] Arreglo = {"A","Andres","Alex","Martin","R","Rene","D","Diego","P","Pablo","I","Ignacio","M","Miguelo","S","Sebastian","David","N","Nicolas","C","Carlos","J","Juan","T","Tomas"};
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
       // mydb.insertContact(item);
        agregar(item);
        if (!arreglofinal.contains(indice)){arreglofinal.add(indice);}
        Collections.sort(arreglofinal);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList(KEY_arreglo,arreglofinal);
    }
    public ArrayList<String> getAllCotacts(){
        ArrayList<String> array_list = new ArrayList<String>();
        String[] projection = new String[] {
                Usuarios.COL_NOMBRE};
        Uri clientesUri =  UsuariosProvider.CONTENT_URI;

        ContentResolver cr = getActivity().getContentResolver();
        Cursor cur = cr.query(clientesUri,
                projection, //Columnas a devolver
                null,       //Condición de la query
                null,       //Argumentos variables de la query
                null);      //Orden de los resultados

        if (cur.moveToFirst())
        {
            do
            {
                String nombre;
                int colNombre = cur.getColumnIndex(Usuarios.COL_NOMBRE);
                nombre = cur.getString(colNombre);
                array_list.add(nombre);
                String indice = Character.toString(nombre.charAt(0));
                if (!array_list.contains(indice)){array_list.add(indice);}
                cur.moveToNext();

            } while (cur.isAfterLast()==false);
        }
        return array_list;

    }
    public void agregar(String item){
        ContentValues values = new ContentValues();

        values.put("nombre", item);
        ContentResolver cr = getActivity().getContentResolver();

        cr.insert(UsuariosProvider.CONTENT_URI, values);
    }
}