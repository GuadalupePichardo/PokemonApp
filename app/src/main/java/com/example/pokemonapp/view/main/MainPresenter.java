package com.example.pokemonapp.view.main;

import android.util.Log;

import com.example.pokemonapp.contracts.MainContract;
import com.example.pokemonapp.data.local.PokemonDB;
import com.example.pokemonapp.data.model.Card;
import com.example.pokemonapp.data.model.CardDataBase;
import com.example.pokemonapp.data.model.Cards;
import com.example.pokemonapp.data.model.Pokemon;
import com.example.pokemonapp.data.remote.Api;
import com.example.pokemonapp.listener.TaskListener;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View view;
    private static final String TAG="Response Api";
    private static final String RESP="Lista";
    private List<CardDataBase> cards = new ArrayList<>();
    private PokemonDB database;

    public MainPresenter(MainContract.View view, PokemonDB database) {
        this.view = view;
        this.database = database;
    }

    @Override
    public void start() {
        getPokemons();
    }

    private void getPokemons(){
        Api.getInstance().getCardsDetails(new TaskListener<Cards>() {
            @Override
            public void onSuccess(Cards response) {
                Log.d(TAG,"Si se consultó");
                if (view!=null) {
                    List<Pokemon> pokemons= configureResponse(response);
                    view.showPokemons(pokemons);
                }
            }
            @Override
            public void onError(String error) {
                Log.d(TAG, "No se consultó");
                if (view !=null) view.showError(error);
            }
        });
    }

    private void saveDatabase(List<Pokemon> pokemonsList) {
        if (pokemonsList != null) {
            CardDataBase cardDataBase = new CardDataBase();
            cards = database.mainDao().getAll();
            for (Pokemon pokemon : pokemonsList) {
                cardDataBase.setName(pokemon.getName());
                cardDataBase.setNumber(pokemon.getNumber());
                cardDataBase.setSuperType(pokemon.getSuperType());
                cardDataBase.setSubType(pokemon.getSubType());
                cardDataBase.setImageUrl(pokemon.getImageUrl());
                database.mainDao().insert(cardDataBase);
                cards.clear();
                cards.addAll(database.mainDao().getAll());
            }
        }
    }

    private List<Pokemon> configureResponse(Cards cards){
        List<Pokemon> pokemons = new ArrayList<>();
        if(cards != null && cards.getCards() != null){
            int index;
            for (index =0; index< cards.getCards().size(); index++){
                Card card = cards.getCards().get(index);
                String itemName="", itemNumber="", itemSupertype="", itemSubType="", itemUrlImage = "";
                if(card != null){
                    itemName = card.getName();
                    itemNumber = card.getNumber();
                    itemSupertype = card.getSupertype();
                    itemSubType = card.getSubtype();
                    itemUrlImage = card.getImageUrlHiRes();
                }
                Log.d(RESP, itemName+ " "+ itemNumber+" "+itemSupertype+ " "+itemUrlImage);
                pokemons.add(new Pokemon(itemName,itemNumber,itemSupertype,itemSubType,itemUrlImage));
            }
        }
        saveDatabase(pokemons);
        return pokemons;
    }
    @Override
    public void onDestroy() {
        view=null;
    }
}
