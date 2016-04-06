package com.example.david.tutorialmecolab;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar); //barra de arriba
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button button = (Button)findViewById(R.id.btn_open_window);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paquete pack = new paquete("Segunda Pantalla", 23);
                //startActivity(SecondActivity.getIntent(MainActivity.this, pack)); //esta parte reemplaza al Intent intent bla bla bla de abajo
                startActivity(ListaActivity.getIntent(MainActivity.this));

                //Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                //intent.putExtra(paquete.KEY_PAQUETE, pack);//con esto pasamos datos
                //startActivity(intent);//con esto se manda la instruccion de iniciar nueva actividad
                //REgistrar SecondActivity en AndroidMainfest o el programa tirara error o se caera

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
