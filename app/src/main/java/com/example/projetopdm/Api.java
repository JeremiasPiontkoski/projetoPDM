package com.example.projetopdm;

import android.text.Editable;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {
//    String BASE_URL_PROJECT = "http://localhost/trabalho-pwii/api/";//192.168.0.107
    String BASE_URL_PROJECT = "http://10.0.2.2:80/trabalho-pwii/api/";

    @GET("repository/{idRepository}")
    Call<Repository> getRepositoryById(@Path("idRepository") int idRepository, @Header("Email") String email, @Header("Password") String password, @Header("Rule") String rule);

    @GET("repositoriesByPerson")
    Call<List<Repository>> getRepositoriesByPerson(@Header("Email")String email, @Header("Password") String password, @Header("Rule") String rule);

    @GET("repositories")
    Call<List<Repository>> getRepositories(@Header("Email") String email, @Header("Password") String password, @Header("Rule") String rule);

    @GET("user")
    Call<User> getUser(@Header("Email") String email, @Header("Password") String password, @Header("Rule") String rule);

    @GET("getLanguage/{idLanguage}")
    Call<Language> getLanguage(@Path("idLanguage") int idLanguage, @Header("Email") String email, @Header("Password") String password, @Header("Rule") String rule);

    @POST("user/name/{name}/email/{email}/password/{password}/typeUser/{typeUser}")
    Call<User> insertUser(@Path("name") String name, @Path("email") String email, @Path("password") String password, @Path("typeUser") int typeUser, @Header("Rule") String rule);
}