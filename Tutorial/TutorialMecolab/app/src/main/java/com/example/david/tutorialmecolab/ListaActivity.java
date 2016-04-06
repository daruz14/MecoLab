package com.example.david.tutorialmecolab;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

/**
 * Created by david on 5/04/16.
 */
public class ListaActivity extends Activity{

        public static Intent getIntent(Context contexto){
            Intent intent = new Intent(contexto, ListaActivity.class);
            return intent;
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ListView listView = (ListView)findViewById(R.id.list);

        MyAdapter adapter = new MyAdapter(this, new paquete[]{new paquete("A", 1), new paquete("B",2)});

        listView.setAdapter(adapter);

    }
}
