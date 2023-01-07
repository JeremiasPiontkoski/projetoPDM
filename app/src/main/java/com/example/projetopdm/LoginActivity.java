package com.example.projetopdm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity{
    TextView txtCreateAcount;
    Button btnLogin;
    EditText edtEmail;
    EditText edtPassword;

    public static String KEY_USER = "user";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent it = getIntent();

        btnLogin = findViewById(R.id.btnLogin);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);

        txtCreateAcount = findViewById(R.id.txtCreateAcount);

        txtCreateAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchActivityPage();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void launchActivityPage() {
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }

    private void login() {
        Call<User> call = RetrofitClient.getInstance().getMyApi().getUser(edtEmail.getText().toString(), edtPassword.getText().toString(), "C");

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();

                User user1 = new User(user.getCode(), user.getId(), user.getName(), user.getEmail(), user.getDescription());

                if(user.isLogged()) {
                    Intent it = new Intent(LoginActivity.this, HomeActivity.class);
                    it.putExtra(KEY_USER, user);
                    startActivity(it);
                }else {
                    Toast.makeText(LoginActivity.this, "Email e/ou senha inválidos!", Toast.LENGTH_SHORT).show();
                    edtEmail.setText("");
                    edtPassword.setText("");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("NAO VAI", t.toString());
            }
        });
    }
}