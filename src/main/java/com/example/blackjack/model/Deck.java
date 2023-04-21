package com.example.blackjack.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards = new ArrayList<>();

    public Deck(){}

    public Deck(List<Card> cards){
        this.cards.addAll(cards);
    }

    public void addCard(Card card){
        this.cards.add(card);
    }

    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    public Card drawCard() {
        return this.cards.remove(0);
    }

}
