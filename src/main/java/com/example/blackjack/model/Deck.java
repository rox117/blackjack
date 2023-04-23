package com.example.blackjack.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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

    public int getSize(){
        return cards.size();
    }

    public boolean contains(Card card){
        return cards.contains(card);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deck deck = (Deck) o;
        return cards.containsAll(deck.getCards()) && deck.getCards().containsAll(cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }

    public List<Card> getCards() {
        return cards;
    }
}
