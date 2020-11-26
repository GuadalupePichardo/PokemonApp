package com.example.pokemonapp.data.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.pokemonapp.data.model.CardDataBase;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface PokemonDao {
    @Insert(onConflict = REPLACE)
    Long insert(CardDataBase cardDataBase);

    @Delete
    void delete(CardDataBase cardDataBase);

    @Delete
    void reset(List<CardDataBase> cardDataBase);

    @Query("SELECT * FROM Cards")
    List<CardDataBase> getAll();
}
