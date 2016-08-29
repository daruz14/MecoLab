package com.example.david.applicationconnect.Networking;

import com.example.david.applicationconnect.Models.Message;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by daruz14 on 21-08-16.
 */
public class PostRequest extends Request {
    /**
     * Perform a POST request to {@link #API_URL}.

     * @return String responses.
     * @throws IOException
     */
    @Override
    public ArrayList<String> perform(Message token) throws IOException {


        ArrayList<String> responses = new ArrayList<>();
        URL url = new URL(API_URL);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("text",token.getContent());
            jsonObj.put("to",token.getReceptor());
            jsonObj.put("from",token.getSender());
                JSONObject object = jsonObj;

                urlConnection.setDoOutput(true);
                urlConnection.setDoInput(true);
                urlConnection.setRequestProperty(CONTENT_TYPE, APPLICATION_JSON);
                urlConnection.setRequestMethod(POST);

                OutputStreamWriter wr= new OutputStreamWriter(urlConnection.getOutputStream());
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

            //}
            return responses;
        }
        catch (Exception ex) { }
        finally {
            if (urlConnection != null)
            {urlConnection.disconnect();}
        }
        return responses;
    }
}
