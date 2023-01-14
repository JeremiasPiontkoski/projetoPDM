package com.example.projetopdm;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RepositoryDetail#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RepositoryDetail extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_REPO_ID = "idRepository";

    // TODO: Rename and change types of parameters
    private int idRepository;

    public RepositoryDetail() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static RepositoryDetail newInstance(int idRepository) {
        RepositoryDetail fragment = new RepositoryDetail();
        Bundle args = new Bundle();
        args.putInt(ARG_REPO_ID, idRepository);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idRepository = getArguments().getInt(ARG_REPO_ID);
        }

        renderRepository();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_repository_detail, container, false);
    }

    private void renderRepository()
    {
        SharedPreferences preferences = getActivity().getSharedPreferences(LoginActivity.KEY_PREFERENCE, Context.MODE_PRIVATE);
        String email = preferences.getString(LoginActivity.KEY_USER_HEADER_EMAIL, "");
        String password = preferences.getString(LoginActivity.KEY_USER_HEADER_PASSWORD, "");
        Call<Repository> call= RetrofitClient.getInstance().getMyApi().getRepositoryById(idRepository, email, password, "C");
        call.enqueue(new Callback<Repository>() {
            @Override
            public void onResponse(Call<Repository> call, Response<Repository> response) {
                Repository repo = response.body();
                ImageView ivPhoto = getActivity().findViewById(R.id.ivPhotoUser);
                TextView tvuserName = getActivity().findViewById(R.id.txtUser);
                TextView tvuserEmail = getActivity().findViewById(R.id.txtEmail);
                TextView tvName = getActivity().findViewById(R.id.txtName);
                TextView tvLanguage = getActivity().findViewById(R.id.txtLanguage);
                TextView tvDescription = getActivity().findViewById(R.id.txtDescription);

                String userName = repo.getUser()
                                .getAsJsonPrimitive("name").getAsString();

                String userEmail = repo.getUser()
                                .getAsJsonPrimitive("email").getAsString();

                String userPhoto = repo.getUser()
                        .getAsJsonPrimitive("photo").getAsString();

                if(userPhoto != null) {
                    Picasso.get().load("http://10.0.2.2:80/trabalho-pwii/" +  userPhoto).into(ivPhoto);
                }else {
                    ivPhoto.setImageResource(R.drawable.image_perfil);
                }

                tvuserName.setText("Usuário: " + userName);
                tvuserEmail.setText("Email: " + userEmail);
                tvName.setText("Nome: " + repo.getName());
                tvLanguage.setText("Linguagem: " + repo.getLanguage());
                tvDescription.setText("Descrição: " + repo.getDescription());
            }

            @Override
            public void onFailure(Call<Repository> call, Throwable t) {
                Log.d("NAO VAI", t.toString());
            }
        });
    }
}