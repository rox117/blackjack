package com.example.blackjack.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.example.blackjack.enums.CardValue;
import com.example.blackjack.enums.Suit;

import java.util.ArrayList;
import java.util.List;

public class DeckTest {
    private Deck deck;
    private List<Card> cards;
    private final Card ACE = new Card(Suit.HEART, CardValue.ACE);

    @BeforeEach
    void setUp() {
        cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (CardValue value : CardValue.values()) {
                cards.add(new Card(suit, value));
            }
        }
        deck = new Deck(cards);
    }

    @Test
    void givenDeck_whenCardAdded_thenCheckSizeIncreasedByOne() {
        int expectedSize = deck.getSize() + 1;
        deck.addCard(new Card(Suit.CLUB, CardValue.ACE));
        int actualSize = deck.getSize();

        assertEquals(expectedSize,actualSize);
    }

    @Test
    void givenDeck_whenCopiedAndShuffled_thenCheckIfCopyEqualOriginal() {
        Deck copyDeck = new Deck(cards);

        deck.shuffle();

        assertNotEquals(copyDeck.getCards(), deck.getCards());
    }

    @Test
    void givenDeck_whenCardIsDrawn_thenCheckIfSizeDecreased() {
        int expectedSize = deck.getSize() -1;
        deck.drawCard();
        int actualSize = deck.getSize();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    void givenDeck_whenCardIsDrawn_thenCheckIfCardRemoved() {
        Card drawnCard = deck.drawCard();

        assertFalse(deck.contains(drawnCard));
    }

    @Test
    void givenNewDeck_whenGetSize_thenReturnZero() {
        deck = new Deck();

        int actualSize = deck.getSize();

        assertEquals(0, actualSize);
    }

    @Test
    void givenNewDeck_whenContainsAnyCard_thenReturnFalse() {
        deck = new Deck();

        assertFalse(deck.contains(ACE));
    }

    @Test
    void givenNewDeck_whenCardAddedCheckSizeIncreased_thenReturnTrue() {
        deck = new Deck();

        deck.addCard(ACE);
        int actualSize = deck.getSize();

        assertEquals(1, actualSize);
    }

    @Test
    void givenNewDeck_whenCardAddedCheckContainsCard_thenReturnTrue() {
        deck = new Deck();

        deck.addCard(ACE);

        assertTrue(deck.contains(ACE));
    }
}
