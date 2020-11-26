package com.example.pokemonapp.data.remote;

import com.example.pokemonapp.data.model.Cards;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface WebService {
    @GET("v1/cards/")
    Single<Cards> getPokemonDetails();
}
