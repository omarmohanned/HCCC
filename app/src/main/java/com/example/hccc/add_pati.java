package com.example.hccc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_pati extends AppCompatActivity {
    private EditText name, email, password, national, age, chronic;
    private RadioGroup smoker, gender;
    private Button add;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference,databaseReference_nat;
    private FirebaseUser firebaseUser;
    String gender1, smoker1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pati);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        national = findViewById(R.id.national);
        age = findViewById(R.id.age);
        chronic = findViewById(R.id.chronic);
        smoker = findViewById(R.id.smoker);
        gender = findViewById(R.id.gender);
        add = findViewById(R.id.ADD);

        ////////
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference_nat=FirebaseDatabase.getInstance().getReference();
        ////////

        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = findViewById(i);
                Toast.makeText(getApplicationContext(), radioButton.getText(), Toast.LENGTH_LONG).show();
                gender1 = radioButton.getText().toString();
            }
        });

        smoker.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = findViewById(i);
                Toast.makeText(getApplicationContext(), radioButton.getText(), Toast.LENGTH_LONG).show();
                smoker1 = radioButton.getText().toString();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                databaseReference.child("pat").child(firebaseUser.getUid()).child("name").setValue(name.getText().toString());
                                databaseReference.child("pat").child(firebaseUser.getUid()).child("email").setValue(email.getText().toString());
                                databaseReference.child("pat").child(firebaseUser.getUid()).child("password").setValue(password.getText().toString());
                                databaseReference.child("pat").child(firebaseUser.getUid()).child("national").setValue(national.getText().toString());
                                databaseReference.child("pat").child(firebaseUser.getUid()).child("age").setValue(age.getText().toString());
                                databaseReference.child("pat").child(firebaseUser.getUid()).child("gender").setValue(gender1);
                                databaseReference.child("pat").child(firebaseUser.getUid()).child("smoker").setValue(smoker1);
                                databaseReference.child("pat").child(firebaseUser.getUid()).child("chronic").setValue(chronic.getText().toString());
                                databaseReference.child("pat").child(firebaseUser.getUid()).child("balance").setValue("10");
                                databaseReference.child("pat").child(firebaseUser.getUid()).child("numoftimes").setValue(false);
                                /////////////////
                                databaseReference.child("all_pat").child("fullname").child(name.getText().toString()).setValue(name.getText().toString());
                                databaseReference_nat.child("pat_nat").child(national.getText().toString()).child("nat").setValue(national.getText().toString());
                                databaseReference_nat.child("pat_nat").child(national.getText().toString()).child("uid").setValue(firebaseUser.getUid().toString());


                                ////////////
                                databaseReference.child("users").child(firebaseUser.getUid()).child("work").setValue("pat");

                            }
                        });

                startActivity(new Intent(getApplicationContext(), NURSE_MAIN.class));
                finish();
            }
        });


    }
}