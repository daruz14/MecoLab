package com.example.david.entrega2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ListView lista_indice = (ListView)findViewById(R.id.list_indices);
        final String[] second_arreglo = {"Hp", "Compaq","Samsung","Sony","Msi"};
        Arrays.sort(second_arreglo);
        final ArrayList arrayList = new ArrayList<>(Arrays.asList(second_arreglo));

        final ArrayAdapter<String> second_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        lista_indice.setAdapter(second_adapter);
        FloatingActionButton Add_element = (FloatingActionButton)findViewById(R.id.fab);
        Add_element.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View layoutResId = (LayoutInflater.from(MainActivity.this)).inflate(R.layout.dialog_main, null);
                AlertDialog.Builder dialogo_agregar =new AlertDialog.Builder(MainActivity.this);
                dialogo_agregar.setView(layoutResId);
                final EditText text = (EditText)layoutResId.findViewById(R.id.txt_add);
                dialogo_agregar.setCancelable(true)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        arrayList.add(text.getText());
                        second_adapter.notifyDataSetChanged();

                    }
                });

                Dialog dialog = dialogo_agregar.create();
                dialog.show();
            }
        });
    }

            @Override
            public boolean onCreateOptionsMenu(Menu menu) {
                // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater().inflate(R.menu.menu_main, menu);
                return true;
            }

            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                // Handle action bar item clicks here. The action bar will
                // automatically handle clicks on the Home/Up button, so long
                // as you specify a parent activity in AndroidManifest.xml.
                int id = item.getItemId();

                //noinspection SimplifiableIfStatement
                if (id == R.id.action_settings) {
                    return true;
                }

                return super.onOptionsItemSelected(item);
            }
    }
