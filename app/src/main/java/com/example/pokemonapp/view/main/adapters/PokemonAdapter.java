package com.example.pokemonapp.view.main.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemonapp.R;
import com.example.pokemonapp.data.model.Pokemon;
import com.example.pokemonapp.view.details.DetailsCardActivity;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder> {

    private List<Pokemon> pokemons;
    private Activity act;
    private static final String TAG="";

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView number;
        TextView superType;
        TextView subType;
        ImageButton card;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.name);
            number = itemView.findViewById(R.id.number);
            superType = itemView.findViewById(R.id.supertype);
            subType = itemView.findViewById(R.id.subtype);
            card = itemView.findViewById(R.id.card);
        }
    }

    public PokemonAdapter(Activity act, List<Pokemon> pokemons) {
        this.pokemons = pokemons;
        this.act = act;
    }

    @NonNull
    @Override
    public PokemonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itempokemon, parent, false);
        return new PokemonAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        String nameCard=pokemons.get(position).getName();
        holder.name.setText(nameCard);
        holder.number.setText("Numero "+pokemons.get(position).getNumber());
        holder.superType.setText("Super tipo "+pokemons.get(position).getNumber());
        holder.subType.setText("Subtipo "+pokemons.get(position).getSubType());

        Log.d(TAG, "Si entró");
        Context context = act;

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imageUrl;
                Intent intent = new Intent(context, DetailsCardActivity.class);
                imageUrl = pokemons.get(position).getImageUrl();
                intent.putExtra("url", imageUrl);
                intent.putExtra("name", nameCard);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public void updateList(List<Pokemon> newPokemons) {
        this.pokemons = newPokemons;
        notifyDataSetChanged();
    }
}
