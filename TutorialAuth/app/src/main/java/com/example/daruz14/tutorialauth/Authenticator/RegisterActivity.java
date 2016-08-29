package com.example.daruz14.tutorialauth.Authenticator;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.daruz14.tutorialauth.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private Button bRegister;
    private TextView tSignIn;
    private EditText email;
    private  EditText number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bRegister = (Button)findViewById(R.id.btn_register);
        tSignIn = (TextView) findViewById(R.id.txt_signin);
        email = (EditText)findViewById(R.id.etxt_email);
        number = (EditText)findViewById(R.id.etxt_password);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();

            }
        });
        tSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
    private void registerUser() {
        String correo = email.getText().toString();
        String numero = number.getText().toString();
        if (TextUtils.isEmpty(correo)){
            Toast.makeText(this,"Ingresa un correo por favor",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(numero)){
            Toast.makeText(this,"Ingresa un numero de telefono por favor",Toast.LENGTH_SHORT).show();
            return;
        }

    }
}
