package com.example.pokemonapp.view.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import com.example.pokemonapp.R;
import com.example.pokemonapp.contracts.MainContract;
import com.example.pokemonapp.data.local.PokemonDB;
import com.example.pokemonapp.data.model.Pokemon;
import com.example.pokemonapp.view.main.adapters.PokemonAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    private MainContract.Presenter presenter;
    private PokemonAdapter pokemonAdapter;
    private PokemonDB database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configureView();
    }

    private void configureView(){
        configureActionBar();

        RecyclerView recyclerPokemon = findViewById(R.id.recyclerPokemon);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,   3);
        recyclerPokemon.setLayoutManager(gridLayoutManager);

        pokemonAdapter = new PokemonAdapter(this, new ArrayList<>());
        recyclerPokemon.setAdapter(pokemonAdapter);

        PokemonDB database = PokemonDB.getInstance(this);
        presenter= new MainPresenter(this, database);
        presenter.start();
    }
    private void configureActionBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void showPokemons(List<Pokemon> pokemons) {
        if(pokemons !=null && pokemonAdapter != null){
            pokemonAdapter.updateList(pokemons);
        }
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {

    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
