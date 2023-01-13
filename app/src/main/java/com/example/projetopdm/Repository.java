package com.example.projetopdm;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class Repository {
    private int id;
    private String name;
    private String language;
    private String description;


    public String code;
    public String type;
    public String message;

    public Repository(int id, String name, String language, String description){
        this.id = id;
        this.name = name;
        this.language = language;
        this.description = description;
    }

    public Repository(String code, String type, String message) {
        this.code = code;
        this.type = type;
        this.message = message;
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
}
