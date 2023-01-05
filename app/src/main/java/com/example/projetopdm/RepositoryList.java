package com.example.projetopdm;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RepositoryList extends Fragment {

    ArrayList<Repository> repositories = new ArrayList<Repository>();
    RecyclerView rvRepositories;
    RepositoryAdapter repositoryAdapter;

    public RepositoryList() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_repository, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        rvRepositories = getActivity().findViewById(R.id.rvRepositories);
        repositoryAdapter = new RepositoryAdapter(repositories);
        RecyclerView.LayoutManager layout =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvRepositories.setLayoutManager(layout);
        rvRepositories.setAdapter(repositoryAdapter);

//        getRepositoriesFromApi();
        getRepositories();
    }

    private void getRepositories()
    {
        Call<List<Repository>> call = RetrofitClient.getInstance().getMyApi().getRepositories("hope@gmail.com", "12345678", "C");
        call.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                List<Repository> repositoryList = response.body();
                for(int i = 0; i < repositoryList.size(); i++) {
                    repositories.add(repositoryList.get(i));
                }
                repositoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
                Log.d("NAO VAI", t.toString());
            }
        });
    }

    private void getRepositoriesFromApi() {
        Call<List<Repository>> call = RetrofitClient.getInstance().getMyApi().getRepository();
        call.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                List<Repository> repositoryList = response.body();
                    for(int i = 0; i < repositoryList.size(); i++) {
                        repositories.add(repositoryList.get(i));
                    }
                repositoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
                Log.d("NAO VAI", t.toString());
            }
        });
    }
}