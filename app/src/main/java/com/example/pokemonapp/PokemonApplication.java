package com.example.pokemonapp;

import android.app.Application;

import com.example.pokemonapp.data.remote.Api;
import com.facebook.stetho.Stetho;

public class PokemonApplication extends Application {
    @Override
    public void onCreate() { //Aqui inicializar componentes!
        super.onCreate();
        Stetho.initializeWithDefaults(this); //??
        Api.getInstance().setmContext(getApplicationContext());
        Api.getInstance().loadSession();
    }

    @Override
    public void onTerminate() { //Aqui guardas informacion antes de que se pierda!
        super.onTerminate();
        Api.getInstance().saveSession();
    }
}
