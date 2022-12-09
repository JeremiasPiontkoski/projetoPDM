package com.example.projetopdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RegisterPersonActivity extends AppCompatActivity {
    TextView txtMakeLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_person);
        Intent it = getIntent();

        txtMakeLogin = findViewById(R.id.txtMakeLogin);
        txtMakeLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {launchLoginActivity();}
        });
    }

    private void launchLoginActivity() {
        Intent it = new Intent(this, LoginActivity.class);
        startActivity(it);
    }
}