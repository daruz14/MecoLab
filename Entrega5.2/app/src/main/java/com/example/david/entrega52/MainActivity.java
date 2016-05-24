package com.example.david.entrega52;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView lista = (ListView)findViewById(R.id.listView);
        ArrayList<String> items= getAllCotacts();
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        lista.setAdapter(itemsAdapter);
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
    public ArrayList<String> getAllCotacts(){
        ArrayList<String> array_list = new ArrayList<String>();
        String[] projection = new String[] {
                "nombre"};
        Uri clientesUri = Uri.parse( "content://com.example.david.entrega51.UsuariosProvider/Usuarios");



        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(clientesUri,
                projection, //Columnas a devolver
                null,       //Condición de la query
                null,       //Argumentos variables de la query
                null);      //Orden de los resultados

        if (cur.moveToFirst())
        {
            String nombre;


            int colNombre = cur.getColumnIndex("nombre");
            do
            {
                nombre = cur.getString(colNombre);
                array_list.add(nombre);

            } while (cur.moveToNext());
        }
        return array_list;

    }
}
