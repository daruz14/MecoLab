package com.example.david.myapplication.Activities;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.app.DialogFragment;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.david.myapplication.Authenticator.AccountAuthenticator;
import com.example.david.myapplication.Fragments.AddUserDialogFragment;
import com.example.david.myapplication.Fragments.MyListFragment;
import com.example.david.myapplication.Fragments.UserFragment;
import com.example.david.myapplication.Model.Item;
import com.example.david.myapplication.Model.Users;
import com.example.david.myapplication.Provider.UsersContract;
import com.example.david.myapplication.R;



public class MainActivity extends AppCompatActivity implements MyListFragment.OnFragmentInteractionListener,
        AddUserDialogFragment.NoticeDialogListener{

    public static final String CODE_NAME = "name";

    private AccountManager mAccountManager;
    private Account mAccount;
    public static String token = "";

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
            resolver.setIsSyncable(mAccount, UsersContract.AUTHORITY, 1);
            resolver.setSyncAutomatically(mAccount, UsersContract.AUTHORITY, true);
        }

        TableObserver observer = new TableObserver(null);
        /*
         * Registra el obsever para students
         */
        //resolver.registerContentObserver(UsersContract.USERS_URI, true, observer);
        resolver.registerContentObserver(UsersContract.USERS_URIL, true, observer);
        setContentView(R.layout.activity_main);
    }

    /**
     * Escucha los cambios que hayan en
     * {@link com.example.david.myapplication.Provider.UsersProvider}.
     */
    public class TableObserver extends ContentObserver {

        public TableObserver(Handler handler) {

            super(handler);
        }

        /**
         * Define el método que es llamado cuando los datos en el content provider cambian.
         * Este método es solo para que haya compatibilidad con plataformas más viejas.
         */
        @Override
        public void onChange(boolean selfChange) {
            onChange(selfChange, null);
        }
        /**
         * Define el método que es llamado cuando los datos en el content provider cambian.
         */
        @Override
        public void onChange(boolean selfChange, Uri changeUri) {

            if (mAccount != null) {
                // Corre la sincronizacion
                Bundle bundle = new Bundle();
                bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
                bundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
                ContentResolver.requestSync(mAccount, UsersContract.AUTHORITY, bundle);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.action_agregar) {
            AddUserDialogFragment dialog = new AddUserDialogFragment();
            dialog.show(this.getFragmentManager(), "dialog");
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Abre una nueva actividad con un StudentFragment dentro de ella que muestra el nombre del
     * estudiante seleccionado.
     * @param item Estudiante seleccionado.
     */
    @Override
    public void onFragmentInteractionList(Item item) {
        String name = item.getText() + " \n" + item.getNumber();
        UserFragment studentFrag = (UserFragment) getSupportFragmentManager().findFragmentById(R.id.student_fragment);
        if (studentFrag != null && studentFrag.isVisible()) {
            studentFrag.setName(item.getText());
        }
        else {
            Intent intent = new Intent(this,UserActivity.class);
            intent.putExtra(CODE_NAME, name);
            startActivity(intent);
        }
    }

    @Override
    public void onGetStudentsFromCloud() {

    }

    /**
     * Llamado cuando se presinó el botón positivo en el dialogo de añadir estudiante.
     * @param dialog
     * @param student
     */
    @Override
    public void onDialogPositiveClick(DialogFragment dialog, Users student) {
        MyListFragment listFrag = (MyListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_list);
        if (listFrag == null) return;
        listFrag.addStudent(student);
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) { }
}
