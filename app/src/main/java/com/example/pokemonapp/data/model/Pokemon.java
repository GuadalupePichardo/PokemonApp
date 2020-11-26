package com.example.pokemonapp.data.model;

public class Pokemon {
    String name;
    String  number;
    String superType;
    String subType;
    String imageUrl;

    public Pokemon(String name, String number, String superType, String subType, String imageUrl) {
        this.name = name;
        this.number = number;
        this.superType = superType;
        this.subType = subType;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSuperType() {
        return superType;
    }

    public void setSuperType(String superType) {
        this.superType = superType;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
