package com.example.daruz14.tutorialauth;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.daruz14.tutorialauth.Authenticator.AccountAuthenticator;
import com.example.daruz14.tutorialauth.Models.User;
import com.example.daruz14.tutorialauth.Networking.PostRequest;
import com.example.daruz14.tutorialauth.Services.FirebaseInstanceIDService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by daruz14 on 23-08-16.
 */
public class AuthenticatorActivity extends AccountAuthenticatorActivity {

    public static final String ARG_ACCOUNT_TYPE = "1";
    public static final String ARG_AUTH_TYPE = "2";
    public static final String ARG_IS_ADDING_NEW_ACCOUNT = "3";
    public static final String PARAM_USER_PASS = "4";
    private AccountManager mAccountManager;
    private FirebaseAuth authentication;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAccountManager = AccountManager.get(this);
        Button button = (Button) findViewById(R.id.btn_register);
        final EditText email = (EditText)findViewById(R.id.etxt_email);
        final EditText number = (EditText)findViewById(R.id.etxt_password);
        final EditText nombre = (EditText)findViewById(R.id.etxt_name);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(email.getText().toString())){
                    Toast.makeText(AuthenticatorActivity.this,"Ingresa un correo por favor",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(number.getText().toString())){
                    Toast.makeText(AuthenticatorActivity.this,"Ingresa un numero de telefono por favor",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(nombre.getText().toString())){
                    Toast.makeText(AuthenticatorActivity.this,"Ingresa tu nombre de usuario por favor",Toast.LENGTH_SHORT).show();
                    return;
                }

                submit();
            }
        });
    }

    /**
     * Método llamado cuando se aprieta el botón de login. Manda al servidor el usuario y contraseña
     * y este le retorna el token necesario para utilizar la API.
     */
    private void submit() {
        // Se obtiene el usuario y contrasena ingresados
        final String userName = ((TextView) findViewById(R.id.etxt_name))
                .getText().toString();
        final String userEmail = ((TextView) findViewById(R.id.etxt_email))
                .getText().toString();
        final String userPass = ((TextView) findViewById(R.id.etxt_password))
                .getText().toString();

        // Se loguea de forma asincronica para no entorpecer el UI thread
        new AsyncTask<Void, Void, Intent>() {
            @Override
            protected Intent doInBackground(Void... params) {
                // Se loguea en el servidor y retorna token
                String authtoken = logIn(userEmail, userPass, userEmail);
                // Informacion necesaria para enviar al authenticator
                final Intent res = new Intent();
                res.putExtra(AccountManager.KEY_ACCOUNT_NAME, userName);
                res.putExtra(AccountManager.KEY_ACCOUNT_TYPE, AccountAuthenticator.ACCOUNT_TYPE);
                res.putExtra(AccountManager.KEY_AUTHTOKEN, authtoken);
                res.putExtra(PARAM_USER_PASS, userPass);
                return res;
            }
            @Override
            protected void onPostExecute(Intent intent) {
                finishLogin(intent);
            }
        }.execute();
    }

    /**
     * Crea cuenta en el AccountManager si es que aún no existe y setea token asociado a esa cuenta.
     * Si cuenta ya existe, solo setea nueva password. Luego, finaliza esta actividad.
     * @param intent
     */
    private void finishLogin(Intent intent) {

        String accountName = intent.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
        String accountPassword = intent.getStringExtra(PARAM_USER_PASS);
        final Account account = new Account(accountName,
                intent.getStringExtra(AccountManager.KEY_ACCOUNT_TYPE));

        // Si es que se esta anadiendo una nueva cuenta
        if (getIntent().getBooleanExtra(ARG_IS_ADDING_NEW_ACCOUNT, false)) {

            String authtoken = intent.getStringExtra(AccountManager.KEY_AUTHTOKEN);
            String authtokenType = AccountAuthenticator.AUTHTOKEN_TYPE;
            // Creando cuenta en el dispositivo y seteando el token que obtuvimos.
            mAccountManager.addAccountExplicitly(account, accountPassword, null);

            // Ojo: hay que setear el token explicitamente si la cuenta no existe,
            // no basta con mandarlo al authenticator
            mAccountManager.setAuthToken(account, authtokenType, authtoken);
        }
        // Si no se está añadiendo cuenta, el token estaba antiguo invalidado.
        // Seteamos contraseña nueva por si la cambio.
        else {
            // Solo seteamos contraseña.  Aca no es necesario setear el token explicitamente,
            // basta con enviarlo al Authenticator
            mAccountManager.setPassword(account, accountPassword);
        }
        // Setea el resultado para que lo reciba el Authenticator
        setAccountAuthenticatorResult(intent.getExtras());
        setResult(RESULT_OK, intent);
        // Cerramos la actividad
        finish();
    }

    /**
     * Pide al servidor el token asociado al usuario y contraseña lo retorna.
     * @param user Username.
     * @param pass Password.
     * @return Token necesario para interactuar con la API.
     */
    private String logIn(String user, String pass,String correo){
        // Método para fines demostrativos :)
        String token = FirebaseInstanceId.getInstance().getToken();
        User usuario = new User(user,correo,pass,token);
        new JsonPost().execute(usuario);
        return token;
    }
    public class JsonPost extends AsyncTask<User, String, User> {

        @Override
        protected User doInBackground(User... users) {
            URL url = null;

            try {
                url = new URL("http://10.203.52.244:3000/api/v1/users");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HttpURLConnection urlConnection = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                JSONObject jsonObj = new JSONObject();
                jsonObj.put(User.FIRST_NAME,users[0].getFirstName());
                jsonObj.put(User.EMAIL,users[0].getEmail());
                jsonObj.put(User.NUMBER,users[0].getNumber());
                jsonObj.put(User.TOKEN,users[0].getUid());
                JSONObject object = jsonObj;

                urlConnection.setDoOutput(true);
                urlConnection.setDoInput(true);
                urlConnection.setRequestProperty(PostRequest.CONTENT_TYPE, PostRequest.APPLICATION_JSON);
                urlConnection.setRequestMethod(PostRequest.POST);

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
                }

                //}

            }
            catch (Exception ex) { }
            finally {
                if (urlConnection != null)
                {urlConnection.disconnect();}
            }
            return null;
        }
    }
}
