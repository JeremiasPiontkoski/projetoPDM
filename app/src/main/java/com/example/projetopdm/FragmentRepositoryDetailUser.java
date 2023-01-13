package com.example.projetopdm;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentRepositoryDetailUser#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentRepositoryDetailUser extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String ARG_REPO_ID = "idRepository";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private int idRepository;

    public FragmentRepositoryDetailUser() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FragmentRepositoryDetailUser newInstance(int idRepository) {
        FragmentRepositoryDetailUser fragment = new FragmentRepositoryDetailUser();
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

        renderRepositories();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_repository_detail_user, container, false);
    }


//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        renderRepositories();
//    }

    private void renderRepositories(){
        Call<Repository> call= RetrofitClient.getInstance().getMyApi().getRepositoryById(idRepository);
        call.enqueue(new Callback<Repository>() {
            @Override
            public void onResponse(Call<Repository> call, Response<Repository> response) {
                Repository repo = response.body();
                TextView tvName = getActivity().findViewById(R.id.txtName);
                TextView tvLanguage = getActivity().findViewById(R.id.txtLanguage);
                TextView tvDescription = getActivity().findViewById(R.id.txtDescription);

                tvName.setText(repo.getName());
                tvLanguage.setText(repo.getLanguage());
                tvDescription.setText(repo.getDescription());

//                TextView txtName = getActivity().findViewById(R.id.textName);

//                txtName.setText("nova atividade");
            }

            @Override
            public void onFailure(Call<Repository> call, Throwable t) {
                Log.d("ERROR USER REPOSITORIES", t.toString());
            }
        });
    }
}