package com.example.david.applicationconnect;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String URL_TO_DOWNLOAD = "http://10.203.10.71:3000/contactos.json";
    List<String> datos;
    ArrayAdapter<String> adapter;
    TextView txt_datos;
    ListView lsita_datos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button descargar = (Button)findViewById(R.id.button);
        txt_datos= (TextView)findViewById(R.id.txt);
        lsita_datos = (ListView)findViewById(R.id.list);

        descargar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                //JsonTask asd =new JsonTask();
                //asd.setmContext(MainActivity.this);
                new JsonTask().execute(URL_TO_DOWNLOAD);
                //datos = getIntent().getStringArrayListExtra("lista");
                //adapter.notifyDataSetChanged();
                //lista_datos.setAdapter(adapter);

            }
        });
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



    public class JsonTask extends AsyncTask<String,String,List<String>> {




        @Override
        protected List<String> doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            ArrayList<String> responses = new ArrayList<>();
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                String response = "";
                String line;
                while ((line = reader.readLine()) != null) {

                    response += line + "\n";
                }
                responses.add(response);
                String response2 = responses.get(0);
                JSONArray jsonArray = new JSONArray(response2);

                List<String> movieModelList = new ArrayList<>();
                for(int i=0; i<jsonArray.length(); i++) {
                    JSONObject finalObject = jsonArray.getJSONObject(i);

                    String name = finalObject.getString("Nombre");
                    String year = finalObject.getString("Telefono");
                    String movieModel = name + " -  " + year;
//
                    movieModelList.add(name);
                }
                return movieModelList;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if(connection != null) {
                    connection.disconnect();
                }
                try {
                    if(reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return  null;
        }

        @Override
        protected void onPostExecute(List<String> strings) {
            super.onPostExecute(strings);
            if (strings!=null){
            txt_datos.setText(strings.get(0));}
            else {txt_datos.setText("estaba vacio esta cosa");}
            datos = strings;
            adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, datos);
            lsita_datos.setAdapter(adapter);


        }

    }
}
