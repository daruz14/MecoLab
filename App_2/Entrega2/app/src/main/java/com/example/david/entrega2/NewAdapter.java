package com.example.david.entrega2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by david on 13/04/16.
 */
public class NewAdapter extends ArrayAdapter<String> {
    private LayoutInflater mLayoutInflater;
    public NewAdapter(Context context, String[] objetos) {
        super(context,R.layout.list_item, objetos);
        mLayoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String pack_var=getItem(position);
        TextView textView = (TextView)convertView.findViewById(R.id.title_item);
        if (convertView == null){

            int viewType = getItemViewType(position);
            if(viewType == 0){//hago un tipo de vista
                convertView = mLayoutInflater.inflate(R.layout.list_item,parent, false);
                textView.setText("asndskasd");
            }
            else{//otra vista
                convertView = mLayoutInflater.inflate(R.layout.list_item,parent, false);
                textView.setText(pack_var);
            }
            //Crear vista, ya que esta puede no estar creada
            //convertView = mLayoutInflater.inflate(R.layout.list_item, parent, false);
        }



        //configurar vista

        return convertView;
    }

    //esto para tener mas de 1 celda
    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);

    }
}
