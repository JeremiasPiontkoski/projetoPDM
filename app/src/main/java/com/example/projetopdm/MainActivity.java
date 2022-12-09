package com.example.projetopdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnCompany;
    Button btnPerson;
    Button btnHaveAcount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCompany = findViewById(R.id.btnCompany);
        btnPerson =  findViewById(R.id.btnPerson);
        btnHaveAcount = findViewById(R.id.btnHaveAcount);

        btnCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {launchRegisterCompanyActivity();}
        });

        btnPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {launchRegisterPersonActivity();}
        });

        btnHaveAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {launchLoginActivity();}
        });
    }

    private void launchRegisterCompanyActivity() {
        Intent it = new Intent(this, RegisterCompanyActivity.class);
        startActivity(it);
    }

    private void launchRegisterPersonActivity() {
        Intent it = new Intent(this, RegisterPersonActivity.class);
        startActivity(it);
    }

    private void launchLoginActivity() {
        Intent it = new Intent(this, LoginActivity.class);
        startActivity(it);
    }
}