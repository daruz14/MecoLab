package com.example.david.entrega8;

import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listMessages;
    EditText textMessages;
    Button sendMessages;
    MyAdapter adapter;
    private final String URL_TO_DOWNLOAD = "http://192.168.1.151:3000/api/v1/messages";
    ArrayList<Item> mIndexedItemArray = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listMessages = (ListView)findViewById(R.id.listView);
        textMessages    =   (EditText)findViewById(R.id.msg_txt);
        sendMessages    =   (Button)findViewById(R.id.btn_send);
        adapter = new MyAdapter(this, R.layout.adaptor ,
                mIndexedItemArray);
        listMessages.setAdapter(adapter);
        sendMessages.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String message =textMessages.getText().toString();
                if (message != null){
                    Item mensaje = new Item(message,1,"Servidor","David");
                    Request request = Request.createRequest(Request.POST);
                    try {
                        ArrayList<String> results = request.perform(mensaje);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

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
            case R.id.sync:
                try {
                    Sync_list();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (OperationApplicationException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void Sync_list() throws IOException, OperationApplicationException, RemoteException, JSONException {
        Request request = Request.createRequest(Request.GET);
        ArrayList<String> results = request.perform(null);
        if (results.size() == 1){
            updateData(results);
        }
    }

    protected void updateData(ArrayList<String> responses)
            throws JSONException, RemoteException, OperationApplicationException {
        if (responses == null) return;
        if (responses.size()>0){
            String response = responses.get(0);
            JSONArray jsonArray = new JSONArray(response);
            for (int i=0; i< jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String info = jsonObject.getString("text");
                String origen = jsonObject.getString("from");
                String destino = jsonObject.getString("to");
                int type = 0;
                if (origen == "David"){
                    type = 1;
                }
                Item dato = new Item(info,type,destino,origen);
                mIndexedItemArray.add(dato);
            }

        }
        adapter.notifyDataSetChanged();
    }
    //@Override
    //protected void onSaveInstanceState(Bundle outState) {
    //    super.onSaveInstanceState(outState);
    //    outState.putBoolean(KEY_BOOLEAN, bfirst);
    //} Terminar de implementar para recordar datos descargados en la lista.
}
