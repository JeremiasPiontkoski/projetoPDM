package com.example.projetopdm;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Language {
    private Integer id;
    private String language;
    private String strLanguage;

    public Language(Integer id, String language) {
        this.id = id;
        this.language = language;
    }

    public Language(){}

    public String getLanguage(int idLanguage) {
        Call<Language> call = RetrofitClient.getInstance().getMyApi().getLanguage(idLanguage, "update2@gmail.com", "12345678", "C");
        call.enqueue(new Callback<Language>() {
            @Override
            public void onResponse(Call<Language> call, Response<Language> response) {
                Language language = response.body();
                strLanguage = language.getLanguage();
            }

            @Override
            public void onFailure(Call<Language> call, Throwable t) {
                Log.d("ERRO NA LANGUAGE", t.toString());
            }
        });
        return strLanguage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
