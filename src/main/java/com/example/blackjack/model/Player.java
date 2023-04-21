package com.example.blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Card> hand = new ArrayList<>();
    private String label;
    private int score;

    public Player(String label) {
        this.label = label;
    }

    public void drawCard(Card card) {
        this.hand.add(card);
        score += card.value().value;
    }

    public int getScore() {
        return score;
    }
    @Override
    public String toString(){
        return label + " " + hand + " score = " + score;
    }

}
