package com.example.projetopdm;

import android.text.Editable;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface Api {
//    String BASE_URL_PROJECT = "http://localhost/trabalho-pwii/api/";//192.168.0.107
    String BASE_URL_PROJECT = "http://10.0.2.2:80/trabalho-pwii/api/";
    
    @GET("getRepo")
    Call<List<Repository>> getRepository();

    @GET("getRepository/{idRepository}")
    Call<Repository> getRepositoryById(@Path("idRepository") int idRepository);

    @GET("repositories")
    Call<List<Repository>> getRepositories(@Header("Email") String email, @Header("Password") String password, @Header("Rule") String rule);

    @GET("user")
    Call<User> getUser(@Header("Email") String email, @Header("Password") String password, @Header("Rule") String rule);

    @GET("getLanguage/{idLanguage}")
    Call<Language> getLanguage(@Path("idLanguage") int idLanguage, @Header("Email") String email, @Header("Password") String password, @Header("Rule") String rule);
}