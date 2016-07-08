package com.example.david.entrega7;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final String URL_TO_DOWNLOAD = "http://10.201.127.129:3000/api/v1/contactos";
    Button boton_get;
    Button boton_post;
    TextView texto;
    private static final String KEY_BOOLEAN = "false";
    private  boolean bfirst = true;
    ArrayList<String> datosD = new ArrayList<String >();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        boton_get = (Button)findViewById(R.id.button);
        boton_post = (Button)findViewById(R.id.button2);
        texto=(TextView)findViewById(R.id.txt);
        boton_get.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                descargarContactos();
                /*try {
                    obtenerContactos();
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/

            }


            
        });
        boton_post.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    obtenerContactos();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        //if (savedInstanceState !=null){
        //    bfirst = false;
        //}
        //if (bfirst==true){
        //    try {
        //        obtenerContactos();
        //    } catch (JSONException e) {
        //        e.printStackTrace();
        //    }
        //    bfirst = false;
        //}

    }

    private void descargarContactos() {
       GetContacts descarga = (GetContacts) new GetContacts();
        descarga.execute(URL_TO_DOWNLOAD);

    }

    private void obtenerContactos() throws JSONException {
        //Recordar la estructura es

        /*contactsCursor = getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI,   // URI de contenido para los contactos
                projection,                        // Columnas a seleccionar
                selectionClause                    // Condición del WHERE
                selectionArgs,                     // Valores de la condición
                sortOrder);                        // ORDER BY columna [ASC|DESC]*/

        String[] projeccion = new String[] { ContactsContract.Data._ID, ContactsContract.Data.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.TYPE };
        String selectionClause = ContactsContract.Data.MIMETYPE + "='" +
                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "' AND "
                + ContactsContract.CommonDataKinds.Phone.NUMBER + " IS NOT NULL";
        String sortOrder = ContactsContract.Data.DISPLAY_NAME + " ASC";

        Cursor c = getContentResolver().query(
                ContactsContract.Data.CONTENT_URI,
                projeccion,
                selectionClause,
                null,
                sortOrder);

        texto.setText("");
        JSONArray JContacts = new JSONArray();

        JSONObject myjson = new JSONObject();
        while(c.moveToNext()){

            JSONArray JContacts2 = new JSONArray();
            myjson.put("Nombre", c.getString(1));
            myjson.put("Apellido","" );
            myjson.put("Telefono", Integer.parseInt(c.getString(2).substring(3)));
            JContacts2.put(myjson);
            String jsonData = JContacts2.toString();
            new PostContacts().execute(jsonData);
            JContacts.put(myjson);
            texto.append("Identificador: " + c.getString(0) + " Nombre: " + c.getString(1) + " Número: " + c.getString(2) + " Tipo: " + c.getString(3) + "\n");
        }
        c.close();
        //String jsonData = JContacts.toString();
        //new PostContacts().execute(jsonData);

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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_BOOLEAN, bfirst);
    }


    public class GetContacts extends AsyncTask<String,String,ArrayList<String>> {
        ArrayList<String>mlist;

        @Override
        protected ArrayList<String> doInBackground(String... params) {

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

                ArrayList<String> movieModelList = new ArrayList<>();
                for(int i=0; i<jsonArray.length(); i++) {
                    JSONObject finalObject = jsonArray.getJSONObject(i);

                    String name = finalObject.getString("Nombre");
                    int year = finalObject.getInt("Telefono");
                    String ano = Integer.toString(year);
                    movieModelList.add(name + " " + ano);
                }
                mlist = movieModelList;
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
        protected void onPostExecute(ArrayList<String> strings) {
            super.onPostExecute(strings);
            datosD = strings;

            if (datosD.size()!=0){
                String res = "";
                for(int i=0; i<datosD.size(); i++) {
                    res += datosD.get(i) + "\n";
                }
                texto.setText(res);
            }
        }
    }
}
//ASYNTASKLOADER