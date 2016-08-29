package com.example.david.applicationconnect.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.david.applicationconnect.Models.Message;
import com.example.david.applicationconnect.Models.User;
import com.example.david.applicationconnect.R;

import java.util.List;

/**
 * Created by daruz14 on 22-08-16.
 */
public class UserAdapter extends ArrayAdapter<User>{
    private LayoutInflater mLayoutInflater;

    public UserAdapter(Context context, int resource, List<User> objects) {
        super(context, resource, objects);
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        User content = getItem(position);

        if (view == null){
            view = mLayoutInflater.inflate(R.layout.user_list_item, parent, false);
        }

        TextView contentTextView = (TextView) view.findViewById(R.id.txt_number);
        TextView nameTextView = (TextView) view.findViewById(R.id.txt_name_user);

        contentTextView.setText(Integer.toString(content.getNumber()));
        nameTextView.setText(String.valueOf(content.getFirstName()));

        return view;
    }
}
