package com.example.david.entrega51;

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
 * Created by david on 11/05/16.
 */
public class MyAdapter extends ArrayAdapter<String> {
    private Context ctx = null;
    private ArrayList<String> elements;
    private LayoutInflater mLayoutInflater;

    public MyAdapter(Context context, ArrayList<String> objects) {
        super(context, R.layout.adapter, objects);
        mLayoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ctx=context;
        elements=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String str_item = getItem(position);
        RecyclerView.ViewHolder holder = null;
        if (convertView == null) {

            int viewType = getItemViewType(position);
            if (viewType == 0) {//hago un tipo de vista


                //if (position == 0) {


                //} else {//otra vista
                //}
                convertView = mLayoutInflater.inflate(R.layout.adapter, parent, false);
            }
        }

        if (str_item.length() == 1){
            convertView = mLayoutInflater.inflate(R.layout.adapter_index, parent, false);
            convertView.setBackgroundColor(Color.CYAN);

        }
        else{
            convertView.setBackgroundColor(Color.TRANSPARENT);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.txt_list);
        //TextView separator= (TextView)convertView.findViewById():
        textView.setText(str_item);
        ViewGroup.LayoutParams params = convertView.getLayoutParams();
        //Crear vista, ya que esta puede no estar creada
        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);

    }
}