package com.example.pokemonapp.contracts;

import com.example.pokemonapp.data.model.Pokemon;

import java.util.List;

public class MainContract {
    public interface View extends BaseView<Presenter>{
        void showPokemons(List<Pokemon> pokemons);
        void showError(String error);
    }
    public interface Presenter extends BasePresenter{
        void onDestroy();
    }
}
