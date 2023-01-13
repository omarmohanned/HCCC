package com.example.hccc;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.os.Bundle;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class view_doctor_pat extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private RecyclerView doctor;
    private List<retrieve_doctor_list> retrieve_doctor_list;
    private fav_adapter fav_adapter;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_doctor_pat);
        doctor=findViewById(R.id.doctor);
        doctor.setHasFixedSize(true);
        doctor.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        ///////////////////
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("doctor");
        retrieve_doctor_list = new ArrayList<>();
        ////////////////
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    retrieve_doctor_list ret = ds.getValue(retrieve_doctor_list.class);
                    retrieve_doctor_list.add(ret);

                }
                fav_adapter = new fav_adapter(view_doctor_pat.this, retrieve_doctor_list);
                doctor.setAdapter(                  fav_adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}