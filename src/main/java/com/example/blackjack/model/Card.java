package com.example.blackjack.model;

import com.example.blackjack.enums.CardValue;
import com.example.blackjack.enums.Suit;

public record Card(Suit face, CardValue value) {
}
