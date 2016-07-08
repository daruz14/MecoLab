package com.example.david.myapplication.SyncAdapter;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.content.SyncResult;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;

import com.example.david.myapplication.Authenticator.AccountAuthenticator;
import com.example.david.myapplication.Model.Users;
import com.example.david.myapplication.Networking.GetRequest;
import com.example.david.myapplication.Networking.Request;
import com.example.david.myapplication.Provider.UsersContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by david on 1/06/16.
 */
public class SyncAdapter extends AbstractThreadedSyncAdapter {

    private ContentResolver mContentResolver;
    private String mToken;
    private AccountManager mAccountManager;

    public SyncAdapter (Context context, boolean autoInitialize){
        super(context, autoInitialize);
        mContentResolver = context.getContentResolver();
        mAccountManager = AccountManager.get(context);
    }

    /**
     * Llamado cuando se sincroniza. Acá se debe hacer el llamado al servidor y la
     * actualizacion de datos locales.
     * @param account
     * @param extras
     * @param authority
     * @param provider
     * @param syncResult
     */
    @Override
    public void onPerformSync(Account account, Bundle extras, String authority,
                              ContentProviderClient provider, SyncResult syncResult) {
        try {
            // No importa que el thread se bloquee ya que es asincrónico.
            mToken = mAccountManager.blockingGetAuthToken(account, AccountAuthenticator.ACCOUNT_TYPE, true);

            Request request = Request.createRequest(Request.GET);
            ArrayList<String> results = request.perform(mToken);
            Log.d("asdsds", "onPerformSync: antes de entrar ");
            if (results.size() == 1){
                Log.d("aviso", "onPerformSync:  se entra para updatdata");
                updateData(results);
            }
            // Manejo de errores
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Processes data coming from the cloud, POST or GET, and upgrade the corresponding values
     * calling corresponding methods.
     */
    protected void updateData(ArrayList<String> responses)
            throws JSONException, RemoteException, OperationApplicationException {
        if (responses == null) return;
        if (responses.size()>0){
            String response = responses.get(0);
            JSONArray jsonArray = new JSONArray(response);
            Uri uri = UsersContract.USERS_URIA;
            //Crear uri distinta para diferenciar del insert normal con el del get
            Cursor cursor = mContentResolver.query(UsersContract.USERS_URI,
                    null, null, null, null);
            ArrayList<String> students = new ArrayList<>();
            cursor.moveToFirst();
            while (cursor.moveToNext()) {
                String names = cursor.getString(cursor.getColumnIndexOrThrow(
                        UsersContract.UsersColumns.NAMES));
                String firstLast = cursor.getString(cursor.getColumnIndexOrThrow(
                        UsersContract.UsersColumns.FIRST_LASTNAME));
                students.add(firstLast + " " + " " + names);
            }
            for (int i=0; i< jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("Nombre");
                String firstLastname = jsonObject.getString("Apellido");
                int phoneNumber = jsonObject.getInt("Telefono");
                if (!students.contains(firstLastname + " " + name)){
                    ContentValues values = new ContentValues();
                    values.put(UsersContract.UsersColumns.NAMES, name);
                    values.put(UsersContract.UsersColumns.FIRST_LASTNAME, firstLastname);
                    values.put(UsersContract.UsersColumns.PHONE_NUMBER, phoneNumber);
                    mContentResolver.insert(uri , values);
                }
            }
            cursor.close();
        }
    }
}
