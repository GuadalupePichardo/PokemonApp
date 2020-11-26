package com.example.pokemonapp.data.model;

import com.google.gson.annotations.SerializedName;

public class Ability {
    @SerializedName("name")
    private String name;
    @SerializedName("text")
    private String text;
    @SerializedName("type")
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Ability{" +
                "name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
