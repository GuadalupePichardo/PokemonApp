package com.example.pokemonapp.listener;

public interface TaskListener <T>{
    void onSuccess(T response);
    void onError(String error);
}
