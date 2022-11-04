package com.example.hccc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup_doctor extends AppCompatActivity {
    private EditText full_name, email, password, phone, natio;
    private Spinner major, Doctor;
    private Button sign_up;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_doctor);

        full_name = findViewById(R.id.full_name);
        email = findViewById(R.id.email);

        password = findViewById(R.id.password);
        phone = findViewById(R.id.phone);
        natio = findViewById(R.id.natio);
        major = findViewById(R.id.major);
        sign_up = findViewById(R.id.sign_up);
        ///////
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        /////


        String[] MAJOR1 = {"select user type", "Dentistry", "Biochemistry", "Physiology", "Heart disease"};

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_activated_1, MAJOR1);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        major.setAdapter(arrayAdapter);

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (full_name.getText().toString().isEmpty() || email.getText().toString().isEmpty() || phone.getText().toString().isEmpty() || natio.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {

                    Toast.makeText(getApplicationContext(), "THERE IS AN EMPTY FIELD", Toast.LENGTH_SHORT).show();
                } else {
                    firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).
                            addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                                    Toast.makeText(getApplicationContext(), "sign up successfully", Toast.LENGTH_SHORT).show();
                                    databaseReference.child("doctor").child(firebaseUser.getUid()).child("full_name").setValue(full_name.getText().toString());
                                    databaseReference.child("doctor").child(firebaseUser.getUid()).child("email").setValue(email.getText().toString());
                                    databaseReference.child("doctor").child(firebaseUser.getUid()).child("phone").setValue(phone.getText().toString());
                                    databaseReference.child("doctor").child(firebaseUser.getUid()).child("uid").setValue(firebaseUser.getUid().toString());
                                    databaseReference.child("doctor").child(firebaseUser.getUid()).child("National").setValue(natio.getText().toString());
                                    databaseReference.child("doctor").child(firebaseUser.getUid()).child("major").setValue(major.getSelectedItem().toString());
                                    databaseReference.child("doctor").child(firebaseUser.getUid()).child("work").setValue("doctor");


                                    //////////////////////
                                    databaseReference.child("users").child(firebaseUser.getUid()).child("name").setValue(full_name.getText().toString());
                                    databaseReference.child("users").child(firebaseUser.getUid()).child("work").setValue("doctor");
                                    ////////
                                    databaseReference.child("doctors").child("full name").child(full_name.getText().toString()).setValue(full_name.getText().toString());
                                   // databaseReference.child("doctors").child("full name").child("uid").setValue(firebaseUser.getUid());

                                }
                            });

                }
            }
        });
    }
}