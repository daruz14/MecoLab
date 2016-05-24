package com.example.david.entrega4;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by david on 10/05/16.
 */
public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_main);
        String item_select = getIntent().getStringExtra("Mensaje");

        FragmentManager fragmentManager = getSupportFragmentManager();
        ItemSelectedFragment fragment_item = (ItemSelectedFragment)fragmentManager.findFragmentByTag("item_fragment_Second");
        fragment_item.UpdateInfo(item_select);


    }
}
