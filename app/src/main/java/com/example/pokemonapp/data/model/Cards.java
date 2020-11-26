package com.example.pokemonapp.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Cards {
    @SerializedName("cards")
    private List<Card> cards = null;

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
