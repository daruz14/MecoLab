package com.example.david.syncadapter.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.david.syncadapter.Authenticator.AccountAuthenticator;

/**
 * Created by david on 31/05/16.
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
