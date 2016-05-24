package com.example.david.entrega2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by david on 13/04/16.
 */
public class SecondActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.second_activity_main);
        String item_select = getIntent().getStringExtra("Mensaje");
        TextView text = (TextView)findViewById(R.id.txt_second);
        text.setText(item_select);
    }
}
