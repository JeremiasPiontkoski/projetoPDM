package com.example.projetopdm;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    TextView txtLaunchActivity;
    Button btnPerson;
    Button btnCompany;
    EditText edtCnpj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Intent it = getIntent();

        txtLaunchActivity = findViewById(R.id.txtMakeLogin);
        btnCompany = findViewById(R.id.btnCompany);
        btnPerson = findViewById(R.id.btnPerson);
        edtCnpj = findViewById(R.id.editCnpj);

        txtLaunchActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchRegisterActivity();
            }
        });

        btnCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {changeTypeCompany();}
        });

        btnPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {changeTypePerson();}
        });
    }

    private void launchRegisterActivity() {
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }

    private void changeTypeCompany() {
        edtCnpj.setVisibility(View.VISIBLE);

        btnCompany.setBackgroundColor(getResources().getColor(R.color.green_600));
        btnCompany.setTextColor(getResources().getColor(R.color.gray_100));

        btnPerson.setBackgroundColor(getResources().getColor(R.color.gray_100));
        btnPerson.setTextColor(getResources().getColor(R.color.green_500));
    }

    private void changeTypePerson() {
        edtCnpj.setVisibility(View.GONE);

        btnPerson.setBackgroundColor(getResources().getColor(R.color.green_600));
        btnPerson.setTextColor(getResources().getColor(R.color.gray_100));

        btnCompany.setBackgroundColor(getResources().getColor(R.color.gray_100));
        btnCompany.setTextColor(getResources().getColor(R.color.green_500));
    }
}