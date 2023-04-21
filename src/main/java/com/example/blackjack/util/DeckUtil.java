package com.example.blackjack.util;

import com.example.blackjack.Enum.CardValue;
import com.example.blackjack.Enum.Suit;
import com.example.blackjack.exception.DeckParseException;
import com.example.blackjack.exception.InvalidCardSuiteException;
import com.example.blackjack.exception.InvalidCardValueException;
import com.example.blackjack.model.Card;
import com.example.blackjack.model.Deck;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeckUtil {
    private static final String DELIMITER = ",";
    private static final int SUITE_BEGINNING_INDEX = 0;
    private static final int SUITE_END_INDEX = 1;
    private static final int CARD_VALUE_BEGINNING_INDEX =1;

    public static Deck readDeckFromFile(String filePath) throws IOException, DeckParseException {
        List<Card> cards = new ArrayList<>();
        List<String> cardsAsString = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                cardsAsString.addAll(List.of(line.split(DELIMITER)));
            }
        }
        for (String card: cardsAsString) {
            cards.add(map(card));
        }
        return new Deck(cards);
    }

    public static Card map(String cardString) throws DeckParseException {
        String suitString = cardString.substring(SUITE_BEGINNING_INDEX,SUITE_END_INDEX);
        String cardValueString = cardString.substring(CARD_VALUE_BEGINNING_INDEX);
        validateCard(suitString, cardValueString);
        Suit suit = Suit.valueOfLabel(suitString);
        CardValue cardValue = CardValue.valueOfLabel(cardValueString);
        Card card = new Card(suit, cardValue);
        return card;
    }

    //Time complexity is constant because the size of a deck is a constant 52 cards
    public static Deck generateDeck(){
        Deck deck = new Deck();
        for (CardValue cardValue:CardValue.values()) {
            for (Suit suit: Suit.values()) {
                deck.addCard(new Card(suit, cardValue));
            }
        }
        return deck;
    }

    public static void validateCard(String suit, String carValue) throws DeckParseException {
        if(Suit.valueOfLabel(suit) == null)
            throw new InvalidCardSuiteException(String.format("The value '%s' for card suite is incorrect.Only S,D,H,C are valid", suit));
        if (CardValue.valueOfLabel(carValue) == null)
            throw new InvalidCardValueException(String.format("The value '%s' for card value is incorrect.Only integers between 2 and 10 inclusive or J,Q,K,A are valid", carValue));
    }
}
