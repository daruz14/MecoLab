package com.example.daruz14.tutorialauth;

import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.daruz14.tutorialauth.Adapters.MessageAdapter;
import com.example.daruz14.tutorialauth.Models.Message;
import com.example.daruz14.tutorialauth.Models.User;
import com.example.daruz14.tutorialauth.Networking.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by daruz14 on 24-08-16.
 */
public class ChatActivity extends AppCompatActivity {
    List<Message> datos;
    MessageAdapter adapter;
    ListView lsita_datos;
    String mPost = "POST";
    String mGet = "GET";

    public static Intent getIntent(Context contexto, User paquete2){
        Intent intent = new Intent(contexto, MainActivity.class);
        intent.putExtra(User.KEY_PAQUETE, paquete2);//con esto pasamos datos
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);

        Button descargar = (Button) findViewById(R.id.btn_sync);
        Button subir = (Button) findViewById(R.id.btn_send);

        lsita_datos = (ListView) findViewById(R.id.list);
        final User usuario = getIntent().getParcelableExtra(User.KEY_PAQUETE);
        Actualizar(usuario);
        descargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Actualizar(usuario);


            }
        });

        subir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText texto = (EditText)findViewById(R.id.editText);
                Message mensaje = new Message("David","",texto.getText().toString(),usuario.getFirstName());

                new JsonPost().execute(mensaje);
                texto.setText("");
            }
        });
    }

    public void Actualizar(User usuario){

        ArrayList<String> lista = new ArrayList<String>();
        lista.add(mGet);
        lista.add(usuario.getFirstName());
        new JsonTask().execute(lista);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();

//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

    //    return super.onOptionsItemSelected(item);
    //}


    public class JsonTask extends AsyncTask<ArrayList<String>, String, List<Message>> {


        @Override
        protected List<Message> doInBackground(ArrayList<String>... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            ArrayList<String> responses = new ArrayList<>();

            try {
                Request request;
                List<Message> movieModelList = new ArrayList<>();
                request = Request.createRequest(params[0].get(0));
                Message mensajeGet = new Message("","","messages", "");
                ArrayList<String> results = request.perform(mensajeGet);
                String response = results.get(0);
                String nombre = params[0].get(1);
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject finalObject = jsonArray.getJSONObject(i);
                    String from = finalObject.getString("from");
                    String text = finalObject.getString("text");
                    String to = finalObject.getString("to");
                    String date = finalObject.getString("created_at");
                    if ((to.equals("David") && from.equals(nombre))||(to.equals(nombre) && from.equals("David"))) {
                        Message mensaje = new Message(from, date, text, to);

                        movieModelList.add(mensaje);
                    }

                }
                if (movieModelList.size()== 0){
                    Message mensaje = new Message("Servidor"," " ,"No hay textos de conversacion", " ");

                    movieModelList.add(mensaje);
                }

                return movieModelList;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Message> messages) {
            super.onPostExecute(messages);

            datos = messages;
            adapter = new MessageAdapter(ChatActivity.this, android.R.layout.simple_list_item_1, datos);
            lsita_datos.setAdapter(adapter);


        }

    }

    public class JsonPost extends AsyncTask<Message, String, List<Message>> {

        @Override
        protected List<Message> doInBackground(Message... params) {

            List<Message> movieModelList = new ArrayList<>();
            Request request = Request.createRequest("POST");
            try {
                ArrayList<String> results = request.perform(params[0]);
                Message mensaje = new Message("Yo", "Hoy", "Mandaste el texto", "");
//
                movieModelList.add(mensaje);
                return movieModelList;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
