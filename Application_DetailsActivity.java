package com.example.assignment3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Application_DetailsActivity extends AppCompatActivity {

    EditText tvName_application,tvCNIC_application;
    EditText etMatric_application,etInter_application;
    Button updateButton_application,deleteButton_application;
    String id,name,cnic,matric,inter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application__details);

        tvName_application = findViewById(R.id.tvName_application);
        tvCNIC_application = findViewById(R.id.tvCNIC_application);
        etMatric_application = findViewById(R.id.etMatric_application);
        etInter_application = findViewById(R.id.etInter_application);
        updateButton_application = findViewById(R.id.updateButton_application);
        deleteButton_application = findViewById(R.id.deleteButton_application);

        getandSetIntentData();

        updateButton_application.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                application_DBHelper myDB = new application_DBHelper(Application_DetailsActivity.this);
                name = tvName_application.getText().toString().trim();
                cnic = tvCNIC_application.getText().toString().trim();
                matric = etMatric_application.getText().toString().trim();
                inter = etInter_application.getText().toString().trim();
                myDB.updateData(id,name,cnic,matric,inter);
            }
        });

        deleteButton_application.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });



    }
    void getandSetIntentData(){
        if(getIntent().hasExtra("id") &&
                getIntent().hasExtra("name") &&
                getIntent().hasExtra("cnic") &&
                getIntent().hasExtra("matric") &&
                getIntent().hasExtra("inter"))
        {
            //Getting Data From Intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            cnic = getIntent().getStringExtra("cnic");
            matric = getIntent().getStringExtra("matric");
            inter = getIntent().getStringExtra("inter");
            // Set Data to EditTexts

            tvName_application.setText(name);
            tvCNIC_application.setText(cnic);
            etMatric_application.setText(matric);
            etInter_application.setText(inter);

        }else{
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Application Form of  " + name+ " ?");
        builder.setMessage("Are you sure you want to Delete Application Form of " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                application_DBHelper myDB = new application_DBHelper(Application_DetailsActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}