package com.example.pokemonapp.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.pokemonapp.data.model.CardDataBase;

@Database(entities = {CardDataBase.class}, version = 1, exportSchema = false)
public abstract class PokemonDB extends RoomDatabase{
    private static PokemonDB database;
    private static String DATABASE_NAME="pokemon";

    public synchronized static PokemonDB getInstance(Context context){
        if(database ==null){
            database = Room.databaseBuilder(context.getApplicationContext(),
                    PokemonDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }

    public abstract PokemonDao mainDao();
}
