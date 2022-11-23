package com.example.projetopdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView txtCreateAcount;

    Button btnPerson;
    Button btnCompany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent it = getIntent();

        txtCreateAcount = findViewById(R.id.txtCreateAcount);

        txtCreateAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchRegisterPage();
            }
        });
    }

    private void launchRegisterPage() {
        Intent it = new Intent(this, Register.class);
        startActivity(it);
    }
}