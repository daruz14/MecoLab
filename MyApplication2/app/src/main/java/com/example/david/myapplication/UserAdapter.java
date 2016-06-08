package com.example.david.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.david.myapplication.Model.Item;

import java.util.ArrayList;

/**
 * Created by david on 1/06/16.
 */
public class UserAdapter extends ArrayAdapter<Item> {

    private LayoutInflater mLayoutInflater;

    public UserAdapter(Context context, int resource, ArrayList<Item> objects){
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
            if (item.getCellType() == Item.LETTER_CELL) {
                view = mLayoutInflater.inflate(R.layout.list_item2, parent, false);
            }
            else {
                view = mLayoutInflater.inflate(R.layout.list_item, parent, false);
            }
        }
        TextView textView = (TextView) view.findViewById(R.id.text_view);
        textView.setText(item.getText());
        if (item.getCellType() ==1){
            TextView textView1 = (TextView) view.findViewById(R.id.text_view_number);
            textView1.setText(item.getNumber());
        }
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