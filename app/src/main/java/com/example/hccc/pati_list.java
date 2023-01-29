package com.example.hccc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class pati_list extends AppCompatActivity {
    private Button search, update_info;
    private EditText age_update, chronic_update, smoker_update;
    private SearchView search_text;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference, databaseReference1;
    private FirebaseUser firebaseUser;
    private TextView name, age, balance, gender, chronic, national, smoker;
    String nat;
    int number_of_times = 0;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pati_list);
        //////////
        age_update = findViewById(R.id.age_update);
        chronic_update = findViewById(R.id.chronic_update);
        smoker_update = findViewById(R.id.smoker_update);

        age_update.setVisibility(View.GONE);
        chronic_update.setVisibility(View.GONE);
        smoker_update.setVisibility(View.GONE);

        /////////////
        search = findViewById(R.id.search);
        update_info = findViewById(R.id.update_info);
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        balance = findViewById(R.id.balance);
        gender = findViewById(R.id.gender);
        chronic = findViewById(R.id.chronic);
        national = findViewById(R.id.national);
        smoker = findViewById(R.id.smoker);
        search_text = findViewById(R.id.search_text);
        ////////
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference1 = FirebaseDatabase.getInstance().getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        ////////


        update_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number_of_times++;
                Toast.makeText(getApplicationContext(), String.valueOf(number_of_times), Toast.LENGTH_LONG).show();
                if (number_of_times <= 2) {
                    age.setVisibility(View.GONE);
                    chronic.setVisibility(View.GONE);
                    smoker.setVisibility(View.GONE);
///////
                    age_update.setVisibility(View.VISIBLE);
                    chronic_update.setVisibility(View.VISIBLE);
                    smoker_update.setVisibility(View.VISIBLE);

                    age_update.setHint("Update your age");
                    chronic_update.setHint("Update your  chronic");
                    smoker_update.setHint("Update your smoker status");

                    databaseReference.child("pat").child(nat).child("age").setValue(age_update.getText().toString());
                    databaseReference.child("pat").child(nat).child("chronic").setValue(chronic_update.getText().toString());
                    databaseReference.child("pat").child(nat).child("smoker").setValue(smoker_update.getText().toString());
                }
            }
        });
        ///////////
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), search_text.getQuery(), Toast.LENGTH_SHORT).show();
                databaseReference.child("pat_nat").child(search_text.getQuery().toString()).child("uid").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        nat = snapshot.getValue(String.class);
                        Toast.makeText(getApplicationContext(), nat, Toast.LENGTH_LONG).show();

                        databaseReference1.child("pat").child(nat).child("age").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                age.setText("THE PATIENT AGE is: " + snapshot.getValue(String.class));
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        databaseReference1.child("pat").child(nat).child("chronic").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                chronic.setText("THE PATIENT chronic disease is: " + snapshot.getValue(String.class));
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        databaseReference1.child("pat").child(nat).child("gender").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                gender.setText("THE PATIENT gender is: " + snapshot.getValue(String.class));
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        databaseReference1.child("pat").child(nat).child("name").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                name.setText("THE PATIENT name is: " + snapshot.getValue(String.class));
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        databaseReference1.child("pat").child(nat).child("national").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                national.setText("THE PATIENT national number is: " + snapshot.getValue(String.class));
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        databaseReference1.child("pat").child(nat).child("smoker").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                smoker.setText("Is the patient smoker or not : " + snapshot.getValue(String.class));
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        databaseReference1.child("pat").child(nat).child("balance").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                balance.setText("THE PATIENT balance is: " + snapshot.getValue(String.class));
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

        });

    }
}