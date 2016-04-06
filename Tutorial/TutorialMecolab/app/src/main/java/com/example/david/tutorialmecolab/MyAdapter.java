package com.example.david.tutorialmecolab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by david on 5/04/16.
 */
public class MyAdapter extends ArrayAdapter<paquete> {

    private LayoutInflater mLayoutInflater;
    //el resuource es el layout
    public MyAdapter(Context context, paquete[] objects) {
        super(context, R.layout.list_item, objects);
        mLayoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        paquete pack_var=getItem(position);
        if (convertView == null){

            int viewType = getItemViewType(position);
            if(viewType == 0){//hago un tipo de vista
            }
            else{//otra vista
                 }
            //Crear vista, ya que esta puede no estar creada
            convertView = mLayoutInflater.inflate(R.layout.list_item, parent, false);
        }

        TextView textView = (TextView)convertView.findViewById(R.id.txt_item);
        textView.setText(pack_var.getmLabel());
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
        //return super.getItemViewType(position);
        paquete pack = getItem(position);
        if(pack.getmNumber()==10){return 0;}
        else {return 1;}
    }
}
