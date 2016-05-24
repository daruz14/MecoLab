package com.example.david.entrega2final;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements MyDialog.AddItemDialogListener{

    ArrayList arreglofinal;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //FloatingActionButton add_element = (FloatingActionButton) findViewById(R.id.fab);
        ListView lista_items = (ListView)findViewById(R.id.list_item);
        String[] Arreglo = {"A","Andres","Alex","Martin","R","Rene","D","Diego","P","Pablo","I","Ignacio","M","Miguelo","S","Sebastian","David","N","Nicolas","C","Carlos","J","Juan","T","Tomas"};
        Arrays.sort(Arreglo);
        arreglofinal = new ArrayList<>(Arrays.asList(Arreglo));
        adapter = new MyAdapter(this, arreglofinal);
        lista_items.setAdapter(adapter);


        lista_items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(arreglofinal.get(position).toString().length() == 1){}
                else {
                    String item = arreglofinal.get(position).toString();
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("Mensaje", item);
                    startActivity(intent);
                }
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.add_item:
                Add_item();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void Add_item() {

        FragmentManager manager = getFragmentManager();
        final MyDialog mydialog = new MyDialog();

        mydialog.show(manager,"MyDialog");
        //Terminar esto!!!!!!!!!<------------------------------->
        //View layoutResId = (LayoutInflater.from(MainActivity.this)).inflate(R.layout.dialog_fragment, null);
        //AlertDialog.Builder dialogo_agregar =new AlertDialog.Builder(MainActivity.this);

    }
    public void onFinishAddItemDialog(String inputText) {
            //hacer caso en donde se agrega un null!!
        arreglofinal.add(inputText);
        String indice = Character.toString(inputText.charAt(0));

        if (!arreglofinal.contains(indice)){arreglofinal.add(indice);}
        Collections.sort(arreglofinal);
        adapter.notifyDataSetChanged();

    }


}