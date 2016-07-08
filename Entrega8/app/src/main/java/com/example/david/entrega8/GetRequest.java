package com.example.david.entrega8;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by david on 4/07/16.
 */
public class GetRequest extends Request{
    @Override
    public ArrayList<String> perform(Item info) throws IOException {
        URL url;
        HttpURLConnection urlConnection = null;
        Log.d("David", "Vamos a descargar ");
        ArrayList<String> responses = new ArrayList<>();
        try {
            String URL_TO_DOWNLOAD = "http://10.201.127.129:3000/api/v1/messages";
            //url = new URL(API_URL);
            url = new URL(URL_TO_DOWNLOAD);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(GET);
            InputStream is;

            int responseCode = urlConnection.getResponseCode();
            //Llego hasta acá
            is = urlConnection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);
            String response = "";
            String line;
            while ((line = reader.readLine()) != null) {
                response += line + "\n";
            }
            reader.close();
            responses.add(response);
            urlConnection.disconnect();


            return responses;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            if (urlConnection != null)  urlConnection.disconnect();
        }
        return responses;
    }
}
