package com.example.david.entrega3;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements ListItem.OnFragmentSendText{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_file);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentManager fragmentManager = getSupportFragmentManager();
        ListItem fragment = (ListItem) fragmentManager.findFragmentByTag("fragment_list");
        fragment.setListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        FragmentManager fragmentManager = getSupportFragmentManager();
        ListItem fragment = (ListItem) fragmentManager.findFragmentByTag("fragment_list");
        fragment.setListener(null);
    }

    @Override
    public void onSentText(String text) {
        Item_selected f2 = (Item_selected)getFragmentManager().findFragmentById(R.id.str_fragment2);
        if (f2 != null) {
            f2.Update_info(text);
        }
        //else {
        //    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        //    intent.putExtra("Mensaje", text);
        //    startActivity(intent);
        //}

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
}
