package com.example.david.entrega8;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by david on 4/07/16.
 */
public class PostRequest extends Request {
    @Override
    public ArrayList<String> perform(Item info) throws IOException, JSONException {
        URL url;
        HttpURLConnection urlConnection = null;

        ArrayList<String> responses = new ArrayList<>();
        JSONObject myjson = new JSONObject();
        myjson.put("text", info.getText());
        myjson.put("to", info.getmTo());
        myjson.put("from", info.getmFrom());

        JSONArray JContacts = new JSONArray();
        JContacts.put(myjson);
        try {
            url = new URL(API_URL);
            JSONArray jsonArray = JContacts;

            for (int i = 0; i< jsonArray.length(); i++){
                JSONObject object = jsonArray.getJSONObject(i);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoOutput(true);
                urlConnection.setDoInput(true);
                urlConnection.setRequestProperty(CONTENT_TYPE, APPLICATION_JSON);
                urlConnection.setRequestProperty(ACCEPT, APPLICATION_JSON);
                urlConnection.setRequestMethod(POST);
                urlConnection.connect();

                OutputStreamWriter wr= new OutputStreamWriter(urlConnection.getOutputStream());
                //Acá muere todo.
                wr.write(object.toString());
                wr.flush();

                int responsecode = urlConnection.getResponseCode();

                if (responsecode==201){
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader((urlConnection.getInputStream())));
                    String output= "";
                    String a;
                    while ((a = br.readLine()) != null) {
                        output += a;
                    }
                    responses.add(output);
                }
            }
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
