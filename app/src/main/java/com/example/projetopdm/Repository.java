package com.example.projetopdm;

import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;

public class Repository {
    private int id;

    private String name;
    private String language;
    private String description;
    private JsonArray repository;

    public Repository(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public JsonArray getRepository() {
        return repository;
    }

    public void setRepository(JsonArray repository) {
        this.repository = repository;
    }
}
