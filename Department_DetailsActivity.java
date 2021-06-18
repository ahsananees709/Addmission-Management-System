package com.example.assignment3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Department_DetailsActivity extends AppCompatActivity {

    TextView etName_department,etCNIC_department;
    TextView etMatric_department,etInter_department;
    // EditText ;
    RadioButton radioButtonCS_department,radioButtonIT_department,radioButtonSE_department;
    Button updateButton_department,deleteButton_department;
    String id,name,cnic,matric,inter,degree,department;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_degree__details);

        etName_department = findViewById(R.id.etName_degree);
        etCNIC_department = findViewById(R.id.etCNIC_degree);
        etMatric_department = findViewById(R.id.etMatric_degree);
        etInter_department = findViewById(R.id.etInter_degree);
        updateButton_department = findViewById(R.id.updateButton_degree);
        radioButtonCS_department = findViewById(R.id.radioButtonBS_degree);
        radioButtonIT_department = findViewById(R.id.radioButtonMS_degree);
        radioButtonSE_department = findViewById(R.id.radioButtonPHD_degree);
        deleteButton_department = findViewById(R.id.deleteButton_degree);

        getandSetIntentData();

        updateButton_department.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                department_DBHelper myDB = new department_DBHelper(Department_DetailsActivity.this);
                name = etName_department.getText().toString().trim();
                cnic = etCNIC_department.getText().toString().trim();
                matric = etMatric_department.getText().toString().trim();
                inter = etInter_department.getText().toString().trim();
                if(radioButtonCS_department.isChecked())
                {
                    department = "CS";
                }
                else if(radioButtonIT_department.isChecked())
                {
                    department = "IT";
                }
                else if(radioButtonSE_department.isChecked())
                {
                    department = "SE";
                }
                myDB.updateData(id,name,cnic,matric,inter,degree,department);
            }
        });

        deleteButton_department.setOnClickListener(new View.OnClickListener() {
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
                getIntent().hasExtra("inter" )&&
                getIntent().hasExtra("department"))
        {
            //Getting Data From Intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            cnic = getIntent().getStringExtra("cnic");
            matric = getIntent().getStringExtra("matric");
            inter = getIntent().getStringExtra("inter");
            department = getIntent().getStringExtra("department");
            // Set Data to EditTexts

            etName_department.setText(name);
            etCNIC_department.setText(cnic);
            etMatric_department.setText(matric);
            etInter_department.setText(inter);
            switch (department) {
                case "CS":
                    radioButtonCS_department.setChecked(true);
                    break;
                case "IT":
                    radioButtonIT_department.setChecked(true);
                    break;
                case "SE":
                    radioButtonSE_department.setChecked(true);
                    break;
            }
        }else{
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
        }
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Selected Degree of " + name+ " ?");
        builder.setMessage("Are you sure you want to Delete Selected Degree of" + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                department_DBHelper myDB = new department_DBHelper(Department_DetailsActivity.this);
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