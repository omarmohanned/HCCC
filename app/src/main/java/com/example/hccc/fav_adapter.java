package com.example.hccc;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.List;
import java.util.Locale;

public class fav_adapter extends RecyclerView.Adapter<fav_adapter.imageviewholder> {
    private Context mcontext;
    private List<retrieve_doctor_list> mlist;

    int balance;

    private DatabaseReference firebaseDatabase, databaseReference2, databaseReference3;
    private FirebaseUser firebaseUser;

    public fav_adapter(Context mcontext, List<retrieve_doctor_list> mlist) {
        this.mcontext = mcontext;
        this.mlist = mlist;
    }

    @NonNull
    @Override
    public imageviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.view_doc_pat, parent, false);
        return new imageviewholder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final imageviewholder holder, int position) {

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        final retrieve_doctor_list ret_bind = mlist.get(position);
        firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        databaseReference2 = FirebaseDatabase.getInstance().getReference();
        databaseReference3 = FirebaseDatabase.getInstance().getReference();
        holder.full_name.setText(ret_bind.getFull_name());
        holder.major.setText(ret_bind.getMajor());
        holder.phone.setText(ret_bind.getPhone());
        holder.book_apo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mcontext, "do you want to book an appointment??", Toast.LENGTH_SHORT).show();
                final AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                alert.setTitle("confirmation");
                alert.setMessage("do you want to book an appointment??");
                alert.setCancelable(false);
                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        databaseReference3.child("pat").child(firebaseUser.getUid()).child("balance").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                balance = Integer.parseInt(snapshot.getValue(String.class));
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        if (balance==0) {
                            Snackbar.make(view, "no enough balance", Snackbar.LENGTH_LONG).show();
                        } else {
                            databaseReference2.child("pat").child(firebaseUser.getUid()).child("doctor_res").child("name").child(ret_bind.getFull_name());
                            databaseReference2.child("pat").child(firebaseUser.getUid()).child("doctor_res").child("major").child(ret_bind.getMajor());
                            databaseReference2.child("pat").child(firebaseUser.getUid()).child("doctor_res").child("phone").child(ret_bind.getPhone());
                            databaseReference2.child("pat").child(firebaseUser.getUid()).child("doctor_res").child("uid").child(ret_bind.getUid());
                            databaseReference2.child("pat").child(firebaseUser.getUid()).child("doctor_res").child("email").child(ret_bind.getEmail());

                        }


                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alert.create().show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class imageviewholder extends RecyclerView.ViewHolder {
        public TextView full_name, major, phone;
        public Button book_apo;


        public imageviewholder(@NonNull View itemView) {
            super(itemView);
            full_name = itemView.findViewById(R.id.full_name);
            major = itemView.findViewById(R.id.major);
            phone = itemView.findViewById(R.id.phone);
            book_apo = itemView.findViewById(R.id.book_apo);

        }
    }
}
