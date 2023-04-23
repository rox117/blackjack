package com.example.blackjack.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.blackjack.enums.Suit;
import com.example.blackjack.enums.CardValue;

class PlayerTest {
    private Player player;
    private Card card1;
    private Card card2;

    @BeforeEach
    void setUp() throws Exception {
        player = new Player("Alice");
        card1 = new Card(Suit.HEART, CardValue.ACE);
        card2 = new Card(Suit.CLUB, CardValue.KING);
    }

    @Test
    void givenCards_whenDrawn_thenCardValueAddedToScore() {
        int expectedScore1 = CardValue.ACE.value;
        int expectedScore2 = CardValue.KING.value + expectedScore1;

        player.drawCard(card1);
        assertEquals(expectedScore1, player.getScore());
        player.drawCard(card2);
        assertEquals(expectedScore2, player.getScore());
    }

    @Test
    void givenCards_whenDrawn_ToStringPrintsCards() {
        player.drawCard(card1);
        player.drawCard(card2);

        assertEquals("Alice [" + card1 + ", " + card2 + "] score = 21", player.toString());
    }
}
