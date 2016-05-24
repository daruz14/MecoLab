package com.example.david.entrega3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

/**
 * Created by david on 25/04/16.
 */
public class SecondActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_main);
        String item_select = getIntent().getStringExtra("Mensaje");
        Item_selected fragment_item = (Item_selected)getFragmentManager().findFragmentById(R.id.str_fragment2);
        fragment_item.Update_info(item_select);

    }





}
