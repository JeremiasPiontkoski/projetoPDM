package com.example.projetopdm;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link perfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class perfilFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "user";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public perfilFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static perfilFragment newInstance(String user) {
        perfilFragment fragment = new perfilFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, (Serializable) user);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getUser();

        Button btn = getActivity().findViewById(R.id.btnMyRepositories);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                RepositoryList repositoryList = new RepositoryList();
                fragmentTransaction.replace(R.id.fragmentContainerView, repositoryList);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

    private void getUser() {
        SharedPreferences preferences = getActivity().getSharedPreferences(LoginActivity.KEY_PREFERENCE, Context.MODE_PRIVATE);
        String userJson = preferences.getString(LoginActivity.KEY_USER_DATA, "");

        Gson gson = new Gson();
        User userObject = gson.fromJson(userJson, User.class);

        TextView txtName = getActivity().findViewById(R.id.txtName);
        TextView txtEmail = getActivity().findViewById(R.id.txtEmail);
        TextView txtDescription = getActivity().findViewById(R.id.txtDescription);
        TextView txtLanguage = getActivity().findViewById(R.id.txtLanguage);

        txtName.setText(userObject.getName());
        txtEmail.setText(userObject.getEmail());

        if(userObject.getDescription() == null) {
            txtDescription.setText("Você não possui descrição :(");
        }else {
            txtDescription.setText(userObject.getDescription());
        }

        try {
            int idLanguage = userObject.getPerson()
                    .getAsJsonPrimitive("idLanguage").getAsInt();

            Call<Language> call = RetrofitClient.getInstance().getMyApi().getLanguage(idLanguage, "update2@gmail.com", "12345678", "C");
            call.enqueue(new Callback<Language>() {
                @Override
                public void onResponse(Call<Language> call, Response<Language> response) {
                    Language language = response.body();
                    txtLanguage.setText(language.getLanguage());
                }

                @Override
                public void onFailure(Call<Language> call, Throwable t) {
                    Log.d("ERROR GETLANGUAGE", t.toString());
                }
            });
        }catch (NullPointerException e) {
            txtLanguage.setText("Você não possui uma linguagem :(");
        }
    }

//    private void getLanguage(int idLanguage){
//        Call<Language> call = RetrofitClient.getInstance().getMyApi().getLanguage(idLanguage, "update2@gmail.com", "12345678", "C");
//        call.enqueue(new Callback<Language>() {
//            @Override
//            public void onResponse(Call<Language> call, Response<Language> response) {
//                Language language = response.body();
//            }
//
//            @Override
//            public void onFailure(Call<Language> call, Throwable t) {
//                Log.d("ERROR GETLANGUAGE", t.toString());
//            }
//        });
//    }
}