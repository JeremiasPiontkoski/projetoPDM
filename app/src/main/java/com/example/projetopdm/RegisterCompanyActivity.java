package com.example.projetopdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RegisterCompanyActivity extends AppCompatActivity {
    TextView txtMakeLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_company);
        Intent it = getIntent();

        txtMakeLogin = findViewById(R.id.txtMakeLogin);
        txtMakeLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {launchLoginActivity();}
        });
    }

    private void launchLoginActivity(){
        Intent it = new Intent(this, LoginActivity.class);
        startActivity(it);
    }
}