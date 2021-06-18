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

public class ApplicationActivity extends AppCompatActivity {
    RecyclerView application_recyclerview;
    FloatingActionButton addButton2;


    application_DBHelper myDB;
    ArrayList<String> id,name,cnic,matric,inter;
    application_CustomAdapter application_customAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);

        application_recyclerview = findViewById(R.id.application_recyclerview);
        addButton2 = findViewById(R.id.addButton2);

        addButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ApplicationActivity.this,AddApplication.class);
                startActivity(intent);

            }
        });

        myDB = new application_DBHelper(ApplicationActivity.this);
        id = new ArrayList<>();
        name = new ArrayList<>();
        cnic = new ArrayList<>();
        matric = new ArrayList<>();
        inter = new ArrayList<>();

        storeDataInArrays();
        application_customAdapter = new application_CustomAdapter(ApplicationActivity.this,this, id,name,cnic,matric,inter);
        application_recyclerview.setAdapter(application_customAdapter);
        application_recyclerview.setLayoutManager(new LinearLayoutManager(ApplicationActivity.this));
    }

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
            while (cursor.moveToNext()){
                id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                cnic.add(cursor.getString(2));
                matric.add(cursor.getString(3));
                inter.add(cursor.getString(4));

            }
        }
    }

}