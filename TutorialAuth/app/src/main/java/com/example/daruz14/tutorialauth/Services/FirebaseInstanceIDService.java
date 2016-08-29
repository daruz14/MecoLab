package com.example.daruz14.tutorialauth.Services;

import android.accounts.AccountManager;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.util.Log;


import com.example.daruz14.tutorialauth.Authenticator.AccountAuthenticator;
import com.example.daruz14.tutorialauth.Models.Message;
import com.example.daruz14.tutorialauth.Models.User;
import com.example.daruz14.tutorialauth.Networking.Request;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

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
 * Created by daruz14 on 23-08-16.
 */
public class FirebaseInstanceIDService extends FirebaseInstanceIdService {
    public String new_token;
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        

        // TODO: Implement this method to send any registration to your app's servers.
        //Revisar si es necesario la implementacion de una funcion que actualize el token en el servidor!!
        new_token = refreshedToken;
        Log.d("Mi firebase bla: ","el token: "+refreshedToken);
    }






}
