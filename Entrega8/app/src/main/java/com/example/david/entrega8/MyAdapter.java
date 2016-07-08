package com.example.david.entrega8;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by david on 4/07/16.
 */
public class MyAdapter extends ArrayAdapter<Item> {
    private LayoutInflater mLayoutInflater;

    public MyAdapter(Context context, int resource, ArrayList<Item> objects){
        super(context, resource, objects);
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Item item = getItem(position);
        if (view == null){
            // Crear nueva celda
            // Si es una celda de letra entonces tiene layout diferente.
            if (item.getCellType() == Item.SERVER_CELL) {
                view = mLayoutInflater.inflate(R.layout.adaptor, parent, false);
            }
            else {
                view = mLayoutInflater.inflate(R.layout.adaptor2, parent, false);
            }
        }
        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(item.getText());
        return view;
    }

    /**
     * Numero de tipos de items.
     */
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    /**
     * Retorna tipo de item de position
     */
    @Override
    public int getItemViewType(int position) {
        Item item = getItem(position);
        return item.getCellType();
    }
}
