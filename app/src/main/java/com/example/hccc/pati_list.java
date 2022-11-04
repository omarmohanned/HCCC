package com.example.hccc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class pati_list extends AppCompatActivity {
    private Button search;
    private RecyclerView rec_view;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pati_list);
        /////////////
        search=findViewById(R.id.search);
        rec_view=findViewById(R.id.rec_view);
        ///////////
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), search.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}