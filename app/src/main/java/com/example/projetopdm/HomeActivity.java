package com.example.projetopdm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    ImageView imgCreateRepository;
    ImageView imgPerfil;
    ImageView imgHome;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        imgCreateRepository = findViewById(R.id.imgCreateRepository);
        imgPerfil = findViewById(R.id.imgPerfil);
        imgHome = findViewById(R.id.imgHome);

        imgCreateRepository.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                createRepositoryFragment createRepositoryFragment = new createRepositoryFragment();
                fragmentTransaction.replace(R.id.fragmentContainerView, createRepositoryFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        imgPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                perfilFragment perfilFragment = new perfilFragment();
                fragmentTransaction.replace(R.id.fragmentContainerView, perfilFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                homeFragment homeFragment = new homeFragment();
                fragmentTransaction.replace(R.id.fragmentContainerView, homeFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }
}