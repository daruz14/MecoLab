package com.example.daruz14.tutorialauth.Networking;

import com.example.daruz14.tutorialauth.Models.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by daruz14 on 21-08-16.
 */
public class GetRequest  extends  Request{
    /**
     * Perform a GET request to {@link #API_URL}.
     * @param token Authorization token
     * @return String responses.
     * @throws IOException
     */
    @Override
    public ArrayList<String> perform(Message token) throws IOException {
        URL url;
        HttpURLConnection urlConnection = null;

        ArrayList<String> responses = new ArrayList<>();
        try {
            if (token.getContent().equals("messages")){
                url = new URL(API_URL);
            }
            else{
                url = new URL(API_URL_CONTACTS);
            }

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(GET);
            InputStream is;

            int responseCode = urlConnection.getResponseCode();

            is = urlConnection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);
            String response = "";
            String line;
            while ((line = reader.readLine()) != null) {
                response += line + "\n";
            }
            reader.close();
            urlConnection.disconnect();
            responses.add(response);
            return responses;
        }
        catch (Exception ex) { }
        finally {
            if (urlConnection != null)  urlConnection.disconnect();
        }
        return responses;
    }
}
