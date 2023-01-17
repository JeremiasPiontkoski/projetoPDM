package com.example.projetopdm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPersonActivity extends AppCompatActivity {
    TextView txtMakeLogin;
    Button btnCreateAcount;

    EditText edtName;
    EditText edtEmail;
    EditText edtPassword;
    EditText edtConfirmPassword;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_person);
        Intent it = getIntent();

        btnCreateAcount = findViewById(R.id.btnCreateAcount);

        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);

        btnCreateAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if(verifyEdits()) {
                        Call<User> call = RetrofitClient.getInstance().getMyApi().insertUser(edtName.getText().toString(), edtEmail.getText().toString(), edtPassword.getText().toString(), 1, "N");
                        call.enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                User user = response.body();
                                if(user.getCode().equals("200")) {
                                    launchLoginActivity();
                                }else {
                                    Toast.makeText(RegisterPersonActivity.this, "Tente novamente", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                Log.d("CREATEUSER", t.toString());
                            }
                        });
                    }
            }
        });

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

    private Boolean verifyEdits() {
        if(edtName.getText().toString().equals("") || edtEmail.getText().toString().equals("") || edtPassword.getText().toString().equals("") || edtConfirmPassword.getText().toString().equals("")) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return false;
        }else if(edtPassword.getText().toString().length() < 8) {
            Toast.makeText(this, "A senha deve ter pelo menos 8 caracteres!", Toast.LENGTH_SHORT).show();
            return false;
        }else if(!edtPassword.getText().toString().equals(edtConfirmPassword.getText().toString())) {
            Toast.makeText(this, "As senhas devem ser iguais!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}