package com.example.david.lista1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by david on 5/04/16.
 */
public class SecondActivity extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.second_activity);
        String item_select = getIntent().getStringExtra("Mensaje");
        TextView text = (TextView)findViewById(R.id.txt_string);
        text.setText(item_select);
    }
}
