package com.example.david.entrega51;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements MyListFragment.OnFragmentSendText, MyDialog.AddItemDialogListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentManager fragmentManager = getSupportFragmentManager();
        MyListFragment fragment = (MyListFragment) fragmentManager.findFragmentByTag("fragment_list");
        fragment.setListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.add_item:
                Add_item();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void Add_item() {
        android.app.FragmentManager manager = getFragmentManager();
        final MyDialog mydialog = new MyDialog();

        mydialog.show(manager,"MyDialog");
    }

    @Override
    public void onSentText(String text) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        ItemSelectedFragment f2 = (ItemSelectedFragment)fragmentManager.findFragmentByTag("item_frag");
        View v = findViewById(R.id.linearlayout);
        if (v ==null)
        {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("Mensaje", text);//con esto pasamos datos
            startActivity(intent);//
        }
        else
        {
            f2.UpdateInfo(text);
        }

    }

    @Override
    public void onFinishAddItemDialog(String inputText) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        MyListFragment fragment = (MyListFragment) fragmentManager.findFragmentByTag("fragment_list");
        fragment.AgregarItem(inputText);
    }
}
