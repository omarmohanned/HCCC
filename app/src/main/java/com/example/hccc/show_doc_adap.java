package com.example.hccc;

import android.content.Context;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class show_doc_adap extends RecyclerView.Adapter<show_doc_adap.imageviewholder> {
    private Context context;
    private List<retrieve_doctor_list> mlist;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;

    public show_doc_adap(Context context, List<retrieve_doctor_list> mlist) {
        this.context = context;
        this.mlist = mlist;
    }

    @NonNull
    @Override
    public imageviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.show_doctor_in_pationt, parent, false);
        return new imageviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull show_doc_adap.imageviewholder holder, int position) {
        ////////////
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        ///////
        final retrieve_doctor_list retrieve_doctor_list = mlist.get(position);
        ////////////
        holder.name.setText(retrieve_doctor_list.getFull_name());
        holder.phone.setText(retrieve_doctor_list.getPhone());
        holder.major.setText(retrieve_doctor_list.getMajor());
        holder.email.setText(retrieve_doctor_list.getEmail());
        //////////
        holder.book_apo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Redirecting To The Doctor", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }


    public class imageviewholder extends RecyclerView.ViewHolder {
        private TextView name, phone, major, email;
        private Button book_apo;

        public imageviewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
            major = itemView.findViewById(R.id.major);
            email = itemView.findViewById(R.id.email);
            book_apo = itemView.findViewById(R.id.book_apo);


        }
    }
}
