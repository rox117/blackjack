package com.example.blackjack.model;

import com.example.blackjack.Enum.CardValue;
import com.example.blackjack.Enum.Suit;

public record Card(Suit face, CardValue value) {
}
