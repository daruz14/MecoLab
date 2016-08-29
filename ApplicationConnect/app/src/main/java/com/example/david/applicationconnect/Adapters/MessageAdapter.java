package com.example.david.applicationconnect.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.david.applicationconnect.Models.Message;
import com.example.david.applicationconnect.R;

import java.util.List;

/**
 * Created by daruz14 on 21-08-16.
 */
public class MessageAdapter extends ArrayAdapter<Message> {

    private LayoutInflater mLayoutInflater;

    public MessageAdapter(Context context, int resource, List<Message> objects){
        super(context, resource, objects);
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Message content = getItem(position);

        if (view == null){
            view = mLayoutInflater.inflate(R.layout.message_list_item, parent, false);
        }

        TextView contentTextView = (TextView) view.findViewById(R.id.message_content);
        TextView nameTextView = (TextView) view.findViewById(R.id.user_name);

        contentTextView.setText(content.getContent());
        nameTextView.setText(String.valueOf(content.getSender()));

        return view;
    }
}
