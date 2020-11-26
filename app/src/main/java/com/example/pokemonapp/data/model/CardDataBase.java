package com.example.pokemonapp.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Cards")
public class CardDataBase implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int ID;
    @ColumnInfo(name ="name")
    private String name;
    @ColumnInfo(name ="number")
    private String  number;
    @ColumnInfo(name ="superType")
    private String superType;
    @ColumnInfo(name ="subType")
    private String subType;
    @ColumnInfo(name ="imageUrl")
    private String imageUrl;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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
