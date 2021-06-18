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

public class Degree_DetailsActivity extends AppCompatActivity {

    TextView etName_degree,etCNIC_degree;
    TextView etMatric_degree,etInter_degree;
   // EditText ;
    RadioButton radioButtonBS_degree,radioButtonMS_degree,radioButtonPHD_degree;
    Button updateButton_degree,deleteButton_degree;
    String id,name,cnic,matric,inter,degree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_degree__details);

        etName_degree = findViewById(R.id.etName_degree);
        etCNIC_degree = findViewById(R.id.etCNIC_degree);
        etMatric_degree = findViewById(R.id.etMatric_degree);
        etInter_degree = findViewById(R.id.etInter_degree);
        updateButton_degree = findViewById(R.id.updateButton_degree);
        radioButtonBS_degree = findViewById(R.id.radioButtonBS_degree);
        radioButtonMS_degree = findViewById(R.id.radioButtonMS_degree);
        radioButtonPHD_degree = findViewById(R.id.radioButtonPHD_degree);
        deleteButton_degree = findViewById(R.id.deleteButton_degree);

        getandSetIntentData();

        updateButton_degree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                degree_DBHelper myDB = new degree_DBHelper(Degree_DetailsActivity.this);
                name = etName_degree.getText().toString().trim();
                cnic = etCNIC_degree.getText().toString().trim();
                matric = etMatric_degree.getText().toString().trim();
                inter = etInter_degree.getText().toString().trim();
                if(radioButtonBS_degree.isChecked())
                {
                    degree = "BS";
                }
                else if(radioButtonMS_degree.isChecked())
                {
                    degree = "MS";
                }
                else if(radioButtonPHD_degree.isChecked())
                {
                    degree = "PHD";
                }
                myDB.updateData(id,name,cnic,matric,inter,degree);
            }
        });

        deleteButton_degree.setOnClickListener(new View.OnClickListener() {
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
                        getIntent().hasExtra("degree"))
        {
            //Getting Data From Intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            cnic = getIntent().getStringExtra("cnic");
            matric = getIntent().getStringExtra("matric");
            inter = getIntent().getStringExtra("inter");
            degree = getIntent().getStringExtra("degree");
            // Set Data to EditTexts

            etName_degree.setText(name);
            etCNIC_degree.setText(cnic);
            etMatric_degree.setText(matric);
            etInter_degree.setText(inter);
            switch (degree) {
                case "BS":
                    radioButtonBS_degree.setChecked(true);
                    break;
                case "MS":
                    radioButtonMS_degree.setChecked(true);
                    break;
                case "PHD":
                    radioButtonPHD_degree.setChecked(true);
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
                degree_DBHelper myDB = new degree_DBHelper(Degree_DetailsActivity.this);
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