package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class AddDegree extends AppCompatActivity {

    TextView etName4;
    TextView etCNIC4;
    TextView etMatric3;
    TextView etInter3;
    Button submitButton2;
    RadioButton radioButtonBS,radioButtonMS,radioButtonPHD;
    Spinner spinner;
    String degree;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_degree);

        etName4 = findViewById(R.id.etName4);
        etCNIC4 = findViewById(R.id.etCNIC4);
        etMatric3 = findViewById(R.id.etMatric3);
        etInter3 = findViewById(R.id.etInter3);
        radioButtonBS = findViewById(R.id.radioButtonBS);
        radioButtonMS = findViewById(R.id.radioButtonMS);
        radioButtonPHD = findViewById(R.id.radioButtonPHD);

        submitButton2 = findViewById(R.id.submitButton);



       // String degree = null;



        String name = getIntent().getStringExtra("name2");
        String cnic = getIntent().getStringExtra("cnic2");
        String matric = getIntent().getStringExtra("matric2");
        String inter = getIntent().getStringExtra("inter2");

        if(radioButtonBS.isChecked()){

            radioButtonMS.setVisibility(View.GONE);
            radioButtonPHD.setVisibility(View.GONE);
        }

        etName4.setText("Name:  " + name);
        etCNIC4.setText("CNIC:  " + cnic);
        etMatric3.setText("Matric Marks:  " + matric);
        etInter3.setText("Inter Marks:  " + inter);
        //String finalDegree = degree;
        submitButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radioButtonBS.isChecked()){
                    degree = "BS";
                    radioButtonMS.setVisibility(View.INVISIBLE);
                    radioButtonPHD.setVisibility(View.INVISIBLE);


                }else if(radioButtonMS.isChecked()){
                    degree = "MS";

                }else if(radioButtonPHD.isChecked()){
                    degree = "PHD";

                }
                degree_DBHelper myDB = new degree_DBHelper(AddDegree.this);
                myDB.addDegree(
                        name,cnic,
                        matric,
                        inter,
                        degree);
                Intent intent = new Intent(AddDegree.this, DegreeActivity.class);
                startActivity(intent);
            }



        });
    }

}