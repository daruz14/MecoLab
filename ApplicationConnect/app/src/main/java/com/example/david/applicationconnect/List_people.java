package com.example.david.applicationconnect;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.david.applicationconnect.Adapters.MessageAdapter;
import com.example.david.applicationconnect.Adapters.UserAdapter;
import com.example.david.applicationconnect.Models.Message;
import com.example.david.applicationconnect.Models.User;
import com.example.david.applicationconnect.Networking.Request;

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
 * Created by daruz14 on 22-08-16.
 */
public class List_people extends AppCompatActivity {
    List<User> datos;
    ListView lista_contactos;
    UserAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persons);
        lista_contactos = (ListView)findViewById(R.id.lst_people);
        datos = null;
        new GetContacts().execute("GET");
        lista_contactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(datos.get(position).toString().length() == 1){}
                else {
                    User contacto = datos.get(position);
                    Intent intent = new Intent(List_people.this, MainActivity.class);
                    intent.putExtra(User.KEY_PAQUETE, contacto);
                    startActivity(intent);
                }
            }
        });
    }


    public class GetContacts extends AsyncTask<String, String, List<User>> {


        @Override
        protected List<User> doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            ArrayList<String> responses = new ArrayList<>();

            try {
                Request request;
                List<User> UserList = new ArrayList<>();
                request = Request.createRequest(params[0]);
                Message mensaje = new Message("","","contactos","");
                ArrayList<String> results = request.perform(mensaje);
                String response = results.get(0);
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject finalObject = jsonArray.getJSONObject(i);
                    String name = finalObject.getString("Nombre");
                    String last_name = finalObject.getString("Apellido");
                    int phone = finalObject.getInt("Telefono");
                    User usuario = new User(name,last_name,phone);
//
                    UserList.add(usuario);

                }

                return UserList;

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
        protected void onPostExecute(List<User> users) {
            super.onPostExecute(users);

            datos = users;
            adapter = new UserAdapter(List_people.this, android.R.layout.simple_list_item_1, datos);
            lista_contactos.setAdapter(adapter);



        }

    }
}


