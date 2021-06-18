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

public class application_CustomAdapter extends RecyclerView.Adapter<application_CustomAdapter.MyViewHolder> {

    private Context context;
    Activity activity;

    private ArrayList id,name,cnic,matric,inter;

    application_CustomAdapter(Activity activity, Context context,ArrayList id,
                            ArrayList name,
                            ArrayList cnic,
                            ArrayList matric,
                            ArrayList inter){
        this.activity = activity;
        this.id = id;
        this.context = context;
        this.name = name;
        this.cnic = cnic;
        this.matric = matric;
        this.inter = inter;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.application_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tvApplicantID2.setText(String.valueOf(id.get(position)));
        holder.tvApplicantName2.setText(String.valueOf(name.get(position)));
        holder.tvApplicantCNIC2.setText(String.valueOf(cnic.get(position)));
        holder.tvApplicantMatric.setText(String.valueOf(matric.get(position)));
        holder.tvApplicantInter.setText(String.valueOf(inter.get(position)));
        holder.degreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,AddDegree.class);
                intent.putExtra("name2",(String.valueOf(name.get(position))));
                intent.putExtra("cnic2",(String.valueOf(cnic.get(position))));
                intent.putExtra("matric2",(String.valueOf(matric.get(position))));
                intent.putExtra("inter2",(String.valueOf(inter.get(position))));
                context.startActivity(intent);
            }
        });
        holder.mainLayout_application.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Application_DetailsActivity.class);
                intent.putExtra("id",String.valueOf(id.get(position)));
                intent.putExtra("name",String.valueOf(name.get(position)));
                intent.putExtra("cnic",String.valueOf(cnic.get(position)));
                intent.putExtra("matric",String.valueOf(matric.get(position)));
                intent.putExtra("inter",String.valueOf(inter.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvApplicantID2,tvApplicantName2,tvApplicantCNIC2,tvApplicantMatric,tvApplicantInter;
        LinearLayout mainLayout_application;
        Button degreeButton;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvApplicantID2 = itemView.findViewById(R.id.tvApplicantID2);
            tvApplicantName2 = itemView.findViewById(R.id.tvApplicantName2);
            tvApplicantCNIC2 = itemView.findViewById(R.id.tvApplicantCNIC2);
            tvApplicantMatric = itemView.findViewById(R.id.tvApplicantMatric);
            tvApplicantInter = itemView.findViewById(R.id.tvApplicantInter);
            mainLayout_application = itemView.findViewById(R.id.mainLayout_applicaion);
            degreeButton = itemView.findViewById(R.id.dergreeButton);
        }
    }
}
