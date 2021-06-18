package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddApplication extends AppCompatActivity {

    TextView etName3;
    TextView etCNIC3;
    EditText etMatric;
    EditText etInter;
    Button submitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_application);

        etName3 = findViewById(R.id.etName3);
        etCNIC3 = findViewById(R.id.etCNIC3);
        etMatric = findViewById(R.id.etMatric);
        etInter = findViewById(R.id.etInter);
        submitButton = findViewById(R.id.submitButton);
        String name = getIntent().getStringExtra("name1");
        String cnic = getIntent().getStringExtra("cnic1");
        etName3.setText("Name:  " + name);
        etCNIC3.setText("CNIC:  " + cnic);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                application_DBHelper myDB = new application_DBHelper(AddApplication.this);
                myDB.addApplication(//etName3.getText().toString().trim(),
                       // etCNIC3.getText().toString().trim(),
                        name,cnic,
                        Integer.valueOf(etMatric.getText().toString().trim()),
                                Integer.valueOf(etInter.getText().toString().trim()));
                Intent intent = new Intent(AddApplication.this,ApplicationActivity.class);
                startActivity(intent);
            }


        });
    }
}