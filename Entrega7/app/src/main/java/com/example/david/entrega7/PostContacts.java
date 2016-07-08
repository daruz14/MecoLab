package com.example.david.entrega7;

import android.os.AsyncTask;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by david on 15/06/16.
 */
public class PostContacts extends AsyncTask<String, Void , Void> {
    final String URLS = "http://192.168.1.151:3000/api/v1/contactos";
    public static final String POST ="POST";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_JSON = "application/json";
    public static final String ACCEPT = "Accept";
    @Override
    protected Void doInBackground(String... params) {
        String jsonData = params[0];
        try {
            URL url = new URL(URLS);
            HttpURLConnection Connection = (HttpURLConnection)url.openConnection();
            Connection.setDoInput(true);
            Connection.setDoOutput(true);
            Connection.setRequestProperty(CONTENT_TYPE, APPLICATION_JSON);
            Connection.setRequestProperty(ACCEPT, APPLICATION_JSON);
            Connection.setRequestMethod(POST);
            Connection.connect();

            OutputStream dos = Connection.getOutputStream();
            dos.write(jsonData.getBytes());

            InputStream is = Connection.getInputStream();
            String result ="";
            int byteCharacter;
            while((byteCharacter = is.read())!=-1){
                result+=(char)byteCharacter;
            }
            Log.d("Json api","Se creo un contacto nuevo en el servidor  " + result);
            is.close();
            dos.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
