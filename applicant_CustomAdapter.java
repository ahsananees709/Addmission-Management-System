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

public class applicant_CustomAdapter extends RecyclerView.Adapter<applicant_CustomAdapter.MyViewHolder> {

    private Context context;
    Activity activity;

    private ArrayList id,name,cnic,email,phone;

    applicant_CustomAdapter(Activity activity, Context context,ArrayList id,
                            ArrayList name,
                            ArrayList cnic,
                            ArrayList email,
                            ArrayList phone){
        this.activity = activity;
        this.id = id;
        this.context = context;
        this.name = name;
        this.cnic = cnic;
        this.email = email;
        this.phone = phone;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.applicant_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tvApplicantID.setText(String.valueOf(id.get(position)));
        holder.tvApplicantName.setText(String.valueOf(name.get(position)));
        holder.tvApplicantCNIC.setText(String.valueOf(cnic.get(position)));
        holder.tvApplicantEmail.setText(String.valueOf(email.get(position)));
        holder.tvApplicantPhone.setText(String.valueOf(phone.get(position)));
        //After Clickin this Button user goes to Application Form
        holder.applicationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,AddApplication.class);
                intent.putExtra("name1",(String.valueOf(name.get(position))));
                intent.putExtra("cnic1",(String.valueOf(cnic.get(position))));
                context.startActivity(intent);
            }
        });
        //Our Layout When Clicked we go for Update and Delete our data
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Applicant_DetailsActivity.class);
                intent.putExtra("id",String.valueOf(id.get(position)));
                intent.putExtra("name",String.valueOf(name.get(position)));
                intent.putExtra("cnic",String.valueOf(cnic.get(position)));
                intent.putExtra("email",String.valueOf(email.get(position)));
                intent.putExtra("phone",String.valueOf(phone.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvApplicantID,tvApplicantName,tvApplicantCNIC,tvApplicantEmail,tvApplicantPhone;
          LinearLayout mainLayout;
        Button applicationButton;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvApplicantID = itemView.findViewById(R.id.tvApplicantID);
            tvApplicantName = itemView.findViewById(R.id.tvApplicantName);
            tvApplicantCNIC = itemView.findViewById(R.id.tvApplicantCNIC);
            tvApplicantEmail = itemView.findViewById(R.id.tvApplicantEmail);
            tvApplicantPhone = itemView.findViewById(R.id.tvApplicantPhone);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            applicationButton = itemView.findViewById(R.id.applicationButton);
        }
    }
}
