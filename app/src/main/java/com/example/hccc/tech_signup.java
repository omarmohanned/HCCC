package com.example.hccc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class tech_signup extends AppCompatActivity {
    private EditText full_name, email, password, phone, natio, exper;
    private Button sign_up;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tech_signup);
        full_name = findViewById(R.id.full_name);
        email = findViewById(R.id.email);
        exper = findViewById(R.id.exper);

        password = findViewById(R.id.password);
        phone = findViewById(R.id.phone);
        natio = findViewById(R.id.natio);

        sign_up = findViewById(R.id.sign_up);
        ///////
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        /////
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (full_name.getText().toString().isEmpty() || email.getText().toString().isEmpty() || phone.getText().toString().isEmpty() || natio.getText().toString().isEmpty() || password.getText().toString().isEmpty()||exper.getText().toString().isEmpty()) {

                    Toast.makeText(getApplicationContext(), "THERE IS AN EMPTY FIELD", Toast.LENGTH_SHORT).show();
                } else {
                    firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).
                            addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                                    Toast.makeText(getApplicationContext(), "sign up successfully", Toast.LENGTH_SHORT).show();
                                    databaseReference.child("tech").child(firebaseUser.getUid()).child("full_name").setValue(full_name.getText().toString());
                                    databaseReference.child("tech").child(firebaseUser.getUid()).child("email").setValue(email.getText().toString());
                                    databaseReference.child("tech").child(firebaseUser.getUid()).child("phone").setValue(phone.getText().toString());
                                    databaseReference.child("tech").child(firebaseUser.getUid()).child("uid").setValue(firebaseUser.getUid().toString());
                                    databaseReference.child("tech").child(firebaseUser.getUid()).child("National").setValue(natio.getText().toString());
                                    databaseReference.child("tech").child(firebaseUser.getUid()).child("experience").setValue(exper.getText().toString());
                                    databaseReference.child("tech").child(firebaseUser.getUid()).child("work").setValue("TECH");
                                    //////////////////////

                                    databaseReference.child("users").child(firebaseUser.getUid()).child("work").setValue("TECH");
                                }
                            });


                }
            }
        });
    }
}