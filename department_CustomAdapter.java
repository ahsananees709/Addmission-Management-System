package com.example.assignment3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class department_CustomAdapter extends RecyclerView.Adapter<department_CustomAdapter.MyViewHolder> {

    private Context context;
    Activity activity;

    private ArrayList id,name,cnic,matric,inter,degree,department;

    department_CustomAdapter(Activity activity, Context context,ArrayList id,
                         ArrayList name,
                         ArrayList cnic,
                         ArrayList matric,
                         ArrayList inter,
                         ArrayList degree,
                         ArrayList department){
        this.activity = activity;
        this.id = id;
        this.context = context;
        this.name = name;
        this.cnic = cnic;
        this.matric = matric;
        this.inter = inter;
        this.degree = degree;
        this.department = department;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.department_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tvApplicantID3.setText(String.valueOf(id.get(position)));
        holder.tvApplicantName3.setText(String.valueOf(name.get(position)));
        holder.tvApplicantCNIC3.setText(String.valueOf(cnic.get(position)));
        holder.tvApplicantMatric2.setText(String.valueOf(matric.get(position)));
        holder.tvApplicantInter2.setText(String.valueOf(inter.get(position)));
        holder.tvApplicantDegree.setText(String.valueOf(degree.get(position)));

        holder.mainLayout_department.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Degree_DetailsActivity.class);
                intent.putExtra("id",String.valueOf(id.get(position)));
                intent.putExtra("name",String.valueOf(name.get(position)));
                intent.putExtra("cnic",String.valueOf(cnic.get(position)));
                intent.putExtra("matric",String.valueOf(matric.get(position)));
                intent.putExtra("inter",String.valueOf(inter.get(position)));
                intent.putExtra("degree",String.valueOf(degree.get(position)));
                intent.putExtra("department",String.valueOf(department.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvApplicantID3,tvApplicantName3,tvApplicantCNIC3,tvApplicantMatric2,tvApplicantInter2;
        TextView tvApplicantDegree,tvApplicantDepartment;
        LinearLayout mainLayout_department;
        Button departmentButton;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvApplicantID3 = itemView.findViewById(R.id.tvApplicantID3);
            tvApplicantName3 = itemView.findViewById(R.id.tvApplicantName3);
            tvApplicantCNIC3 = itemView.findViewById(R.id.tvApplicantCNIC3);
            tvApplicantMatric2 = itemView.findViewById(R.id.tvApplicantMatric2);
            tvApplicantInter2 = itemView.findViewById(R.id.tvApplicantInter2);
            tvApplicantDegree = itemView.findViewById(R.id.tvApplicantDegree);
            mainLayout_department = itemView.findViewById(R.id.mainLayout_department);
           // departmentButton = itemView.findViewById(R.id.departmentButton);
        }
    }
}
