package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText etUseName,etPassword;
    Button buttonLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUseName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
        buttonLogin = findViewById(R.id.buttonLogin);



        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String un = etUseName.getText().toString().trim();
                String pass = etPassword.getText().toString().trim();
                if(un.equals("ahsan") && pass.equals("12345")){
                    Intent intent = new Intent(LoginActivity.this,ApplicantActivity.class);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this,"Successfully Login",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(LoginActivity.this,"Your Username or Password was Wrong",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}