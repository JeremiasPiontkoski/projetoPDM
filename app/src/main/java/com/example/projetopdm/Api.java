package com.example.projetopdm;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface Api {
//    String BASE_URL_PROJECT = "http://localhost/trabalho-pwii/api/";//192.168.0.107
    String BASE_URL_PROJECT = "http://10.0.2.2:80/trabalho-pwii/api/";
    
    @GET("getRepo")
    Call<List<Repository>> getRepository();
}