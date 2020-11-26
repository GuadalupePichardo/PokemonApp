package com.example.pokemonapp.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Attack {
    @SerializedName("cost")
    private List<String> cost = null;
    @SerializedName("name")
    private String name;
    @SerializedName("text")
    private String text;
    @SerializedName("damage")
    private String damage;
    @SerializedName("convertedEnergyCost")
    private Integer convertedEnergyCost;

    public List<String> getCost() {
        return cost;
    }

    public void setCost(List<String> cost) {
        this.cost = cost;
    }

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

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public Integer getConvertedEnergyCost() {
        return convertedEnergyCost;
    }

    public void setConvertedEnergyCost(Integer convertedEnergyCost) {
        this.convertedEnergyCost = convertedEnergyCost;
    }

    @Override
    public String toString() {
        return "Attack{" +
                "cost=" + cost +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", damage='" + damage + '\'' +
                ", convertedEnergyCost=" + convertedEnergyCost +
                '}';
    }
}
