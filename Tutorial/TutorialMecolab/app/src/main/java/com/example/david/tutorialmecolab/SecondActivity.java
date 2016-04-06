package com.example.david.tutorialmecolab;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by david on 5/04/16.
 */
public class SecondActivity extends Activity{
    private static final String KEY_COLOR = "color";
    private  boolean mColorYellow;

    public static Intent getIntent(Context contexto, paquete paquete2){
        Intent intent = new Intent(contexto, SecondActivity.class);
        intent.putExtra(paquete.KEY_PAQUETE, paquete2);//con esto pasamos datos
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        if (savedInstanceState != null){
            mColorYellow = savedInstanceState.getBoolean(KEY_COLOR);
        }
        if (mColorYellow){
            View view = findViewById(R.id.layout);
            view.setBackgroundColor(Color.YELLOW);
        }

        paquete pack_r = getIntent().getParcelableExtra(paquete.KEY_PAQUETE);
        //String label = getIntent().getStringExtra("clave");
        TextView texto = (TextView)findViewById(R.id.txt_label);
        texto.setText(pack_r.getmLabel());

        Button boton2 = (Button)findViewById(R.id.btn_change_background);
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = findViewById(R.id.layout);
                view.setBackgroundColor(Color.YELLOW);
                mColorYellow = true;
            }
        });
    }


    //Esto guarda el estado cuandoo la actividad se destruye, importannte
    //El bundle es similar al parcel, solo que este va al disco y no a la ram
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_COLOR, mColorYellow);
    }
}
