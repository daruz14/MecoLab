package com.example.david.myapplication.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.david.myapplication.Authenticator.AccountAuthenticator;

/**
 * Created by david on 1/06/16.
 */
public class AuthenticatorService extends Service {
    private AccountAuthenticator mAuthenticator;

    @Override
    public void onCreate() {
        // Create a new authenticator object
        mAuthenticator = new AccountAuthenticator(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mAuthenticator.getIBinder();
    }
}
