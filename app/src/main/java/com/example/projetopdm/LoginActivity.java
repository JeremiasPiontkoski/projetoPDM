package com.example.projetopdm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    TextView txtCreateAcount;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent it = getIntent();

        txtCreateAcount = findViewById(R.id.txtCreateAcount);

        txtCreateAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchActivityPage();
            }
        });
    }

    private void launchActivityPage() {
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }
}