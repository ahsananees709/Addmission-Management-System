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

public class DepartmentActivity extends AppCompatActivity {
    RecyclerView department_recyclerview;
    FloatingActionButton addButton3;


    department_DBHelper myDB;
    ArrayList<String> id,name,cnic,matric,inter,degree,department;
    department_CustomAdapter department_customAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);

        department_recyclerview = findViewById(R.id.department_recyclerview);
        addButton3 = findViewById(R.id.addButton3);

        addButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DepartmentActivity.this,AddDepartment.class);
                startActivity(intent);

            }
        });

        myDB = new department_DBHelper(DepartmentActivity.this);
        id = new ArrayList<>();
        name = new ArrayList<>();
        cnic = new ArrayList<>();
        matric = new ArrayList<>();
        inter = new ArrayList<>();
        degree = new ArrayList<>();
        department = new ArrayList<>();

        storeDataInArrays();
        department_customAdapter = new department_CustomAdapter(DepartmentActivity.this,this, id,name,cnic,matric,inter,degree,department);
        department_recyclerview.setAdapter(department_customAdapter);
        department_recyclerview.setLayoutManager(new LinearLayoutManager(DepartmentActivity.this));
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
                degree.add(cursor.getString(5));
                department.add(cursor.getString(6));

            }
        }
    }

}