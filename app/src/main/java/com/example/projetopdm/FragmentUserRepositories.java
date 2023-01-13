package com.example.projetopdm;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentUserRepositories extends Fragment {
    ArrayList<Repository> repositories = new ArrayList<Repository>();
    RecyclerView rvRepositories;
    UserRepositoriesAdapter userRepositoriesAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentUserRepositories() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentUserRepositories.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentUserRepositories newInstance(String param1, String param2) {
        FragmentUserRepositories fragment = new FragmentUserRepositories();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_repositories, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        rvRepositories = getActivity().findViewById(R.id.rvUserRepositories);
        userRepositoriesAdapter = new UserRepositoriesAdapter(repositories);
        RecyclerView.LayoutManager layout =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvRepositories.setLayoutManager(layout);
        rvRepositories.setAdapter(userRepositoriesAdapter);
        getRepositories();
    }

    private void getRepositories()
    {
        SharedPreferences preferences = getActivity().getSharedPreferences(LoginActivity.KEY_PREFERENCE, Context.MODE_PRIVATE);
        String email = preferences.getString(LoginActivity.KEY_USER_HEADER_EMAIL, "");
        String password = preferences.getString(LoginActivity.KEY_USER_HEADER_PASSWORD, "");
        Call<List<Repository>> call = RetrofitClient.getInstance().getMyApi().getRepositoriesByPerson(email, password, "C");
        call.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                List<Repository> repositoryList = response.body();
                for(int i = 0; i < repositoryList.size(); i++) {
                    repositories.add(repositoryList.get(i));
                }
                userRepositoriesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
                Log.d("NAO VAI", t.toString());
            }
        });
    }
}