package com.example.assignment3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ApplicantActivity extends AppCompatActivity {
    RecyclerView applicant_recyclerview;
    FloatingActionButton addButton;


    applicant_DBHelper myDB;
    ArrayList<String> id,name,cnic,email,phone;
    applicant_CustomAdapter applicant_customAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicant);

        applicant_recyclerview = findViewById(R.id.application_recyclerview);
        addButton = findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ApplicantActivity.this,AddApplicant.class);
                startActivity(intent);

            }
        });
//For storing our Data in different Arrays which comes from DBHelper class after Reading data FROM Database
        myDB = new applicant_DBHelper(ApplicantActivity.this);
        id = new ArrayList<>();
        name = new ArrayList<>();
        cnic = new ArrayList<>();
        email = new ArrayList<>();
        phone = new ArrayList<>();

        storeDataInArrays();
        //To show data in our Recycler view which is in Arrays
        applicant_customAdapter = new applicant_CustomAdapter(ApplicantActivity.this,this, id,name,cnic,email,phone);
        applicant_recyclerview.setAdapter(applicant_customAdapter);
        applicant_recyclerview.setLayoutManager(new LinearLayoutManager(ApplicantActivity.this));
    }

    //This function create Because updatin data at the same time in Database and Recycler View
    //Because when data update in Database then we restart our ApplicantActivity and updated data comes in Recycer view
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1)
        {
            recreate();
        }
    }

    void storeDataInArrays(){

        Cursor cursor = myDB.readAllData();

        if(cursor.getCount() == 0)
        {
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
        }else {
            //0 , 1 , 2 are our column indexes and we read data from DataBase and Retrive data by column index
            //And Store data one by One in our Arrays
            while (cursor.moveToNext()){
                id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                cnic.add(cursor.getString(2));
                email.add(cursor.getString(3));
                phone.add(cursor.getString(4));

            }
        }
    }

}