package com.example.blackjack.util;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.example.blackjack.enums.CardValue;
import com.example.blackjack.enums.Suit;
import com.example.blackjack.exception.DeckParseException;
import com.example.blackjack.exception.InvalidCardSuiteException;
import com.example.blackjack.exception.InvalidCardValueException;
import com.example.blackjack.exception.InvalidDeckSizeException;
import com.example.blackjack.model.Card;
import com.example.blackjack.model.Deck;

class DeckUtilTest {
    private static final String VALID_DECK_FILE_PATH = "src/main/resources/cards.csv";
    private static final String INVALID_DECK_FILE_PATH = "src/main/resources/invalidcards.csv";
    private static List<Card> validCards;

    @BeforeAll
    static void setUp() {
        validCards = new ArrayList<>();
        for (CardValue cardValue: CardValue.values()) {
            for (Suit suit: Suit.values()) {
                validCards.add(new Card(suit, cardValue));
            }
        }
    }

    @Test
    void givenDeckFromFile_whenParsed_thenReturnValidDeck() throws IOException, DeckParseException {
        Deck deck = DeckUtil.readDeckFromFile(VALID_DECK_FILE_PATH);
        assertEquals(new Deck(validCards), deck);
    }

    @Test
    void givenInvalidDeckFromFile_whenParsed_thenThrowDeckParseException() {
        assertThrows(DeckParseException.class, () -> DeckUtil.readDeckFromFile(INVALID_DECK_FILE_PATH));
    }

    @Test
    void givenValidCardString_whenMapped_thenReturnValidCard() throws DeckParseException {
        Card card = DeckUtil.map("H2");
        Card expected = new Card(Suit.HEART, CardValue.TWO);

        assertEquals(expected, card);
    }

    @Test
    void givenInvalidCardSuiteString_whenMapped_thenThrowInvalidCardSuiteException() {
        assertThrows(InvalidCardSuiteException.class, () -> DeckUtil.map("X2"));
    }

    @Test
    void givenInvalidCardValueString_whenMapped_thenThrowInvalidCardValueException() {
        assertThrows(InvalidCardValueException.class, () -> DeckUtil.map("H1"));
    }

    @Test
    void givenValidCards_whenGenerateDeck_thenReturnValidDeck() {
        Deck actual = DeckUtil.generateDeck();
        Deck expected = new Deck(validCards);

        assertEquals(expected, actual);
    }

    @Test
    void givenValidSuiteAndValue_whenValidateCard_thenNotThrowException() throws DeckParseException {
        DeckUtil.validateCard("H", "2");
    }

    @Test
    void givenInvalidSuite_whenValidateCard_thenThrowInvalidCardSuiteException() {
        assertThrows(InvalidCardSuiteException.class, () -> DeckUtil.validateCard("X", "2"));
    }

    @Test
    void givenInvalidCardValue_whenValidateCard_thenThrowInvalidCardValueException() {
        assertThrows(InvalidCardValueException.class, () -> DeckUtil.validateCard("H", "1"));
    }

    @Test
    void givenValidDeck_whenValidated_thenNotThrowException() throws InvalidDeckSizeException {
        List<Card> cards = new ArrayList<>(validCards);
        DeckUtil.validateDeckSize(cards);
    }

    @Test
    void givenInvalidDeck_whenValidated_shouldThrowInvalidDeckSizeException() {
        List<Card> cards = new ArrayList<>(validCards);
        cards.remove(0);

        assertThrows(InvalidDeckSizeException.class, () -> DeckUtil.validateDeckSize(cards));
    }
}
