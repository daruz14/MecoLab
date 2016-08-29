package com.example.daruz14.tutorialauth.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.daruz14.tutorialauth.Authenticator.AccountAuthenticator;

/**
 * Created by daruz14 on 23-08-16.
 */
public class AuthenticatorServices extends Service{
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
