package com.example.daruz14.tutorialauth;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.daruz14.tutorialauth.Adapters.MessageAdapter;
import com.example.daruz14.tutorialauth.Adapters.UserAdapter;
import com.example.daruz14.tutorialauth.Authenticator.AccountAuthenticator;
import com.example.daruz14.tutorialauth.Authenticator.LoginActivity;
import com.example.daruz14.tutorialauth.Models.Message;
import com.example.daruz14.tutorialauth.Models.User;
import com.example.daruz14.tutorialauth.Networking.Request;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private AccountManager mAccountManager;
    private Account mAccount;
    public static String token = "";
    List<User> datos;
    ListView lista_contactos;
    UserAdapter adapter;


    private AccountManagerCallback<Bundle> mGetAuthTokenCallback =
            new AccountManagerCallback<Bundle>() {
                @Override
                public void run(final AccountManagerFuture<Bundle> arg0) {
                    try {
                        token = (String) arg0.getResult().get(AccountManager.KEY_AUTHTOKEN);
                    } catch (Exception e) {
                        // handle error
                    }
                }
            };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ContentResolver resolver = getContentResolver();

        mAccountManager = (AccountManager) getSystemService(
                ACCOUNT_SERVICE);

        // Se chequea si existe una cuenta asociada a ACCOUNT_TYPE.
        Account[] accounts = mAccountManager.getAccountsByType(AccountAuthenticator.ACCOUNT_TYPE);
        if (accounts.length == 0){
            // También se puede llamar a metodo mAccountManager.addAcount(...)
            Intent intent = new Intent(this, AuthenticatorActivity.class);
            intent.putExtra(AuthenticatorActivity.ARG_IS_ADDING_NEW_ACCOUNT, true);
            startActivity(intent);
        }
        else {
            mAccount = accounts[0];
            mAccountManager.getAuthToken(mAccount, AccountAuthenticator.ACCOUNT_TYPE, null, this,
                    mGetAuthTokenCallback, null);

            //resolver.requestsync para que se sincronice!
        }
        setContentView(R.layout.activity_main);
        lista_contactos = (ListView)findViewById(R.id.lst_people);
        datos = null;
        new GetContacts().execute("GET");
        lista_contactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(datos.get(position).toString().length() == 1){}
                else {
                    User contacto = datos.get(position);
                    Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                    intent.putExtra(User.KEY_PAQUETE, contacto);
                    startActivity(intent);
                }
            }
        });

    }

    public class GetContacts extends AsyncTask<String, String, List<User>> {


        @Override
        protected List<User> doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            ArrayList<String> responses = new ArrayList<>();

            try {
                Request request;
                List<User> UserList = new ArrayList<>();
                request = Request.createRequest(params[0]);
                Message mensaje = new Message("","","contactos","");
                ArrayList<String> results = request.perform(mensaje);
                String response = results.get(0);
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject finalObject = jsonArray.getJSONObject(i);
                    String name = finalObject.getString(User.FIRST_NAME);
                    String email = finalObject.getString(User.EMAIL);
                    String phone = finalObject.getString(User.NUMBER);
                    String token = finalObject.getString(User.TOKEN);
                    User usuario = new User(name,email,phone,token);
//
                    UserList.add(usuario);

                }

                return UserList;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<User> users) {
            super.onPostExecute(users);

            datos = users;
            adapter = new UserAdapter(MainActivity.this, android.R.layout.simple_list_item_1, datos);
            lista_contactos.setAdapter(adapter);



        }

    }



}
