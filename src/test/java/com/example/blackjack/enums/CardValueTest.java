package com.example.blackjack.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CardValueTest {
    @Test
    void givenValidStringLabel_whenEnumFetched_thenReturnEnumValue() {
        Assertions.assertEquals(CardValue.TWO, CardValue.valueOfLabel("2"));
        Assertions.assertEquals(CardValue.THREE, CardValue.valueOfLabel("3"));
        Assertions.assertEquals(CardValue.FOUR, CardValue.valueOfLabel("4"));
        Assertions.assertEquals(CardValue.FIVE, CardValue.valueOfLabel("5"));
        Assertions.assertEquals(CardValue.SIX, CardValue.valueOfLabel("6"));
        Assertions.assertEquals(CardValue.SEVEN, CardValue.valueOfLabel("7"));
        Assertions.assertEquals(CardValue.EIGHT, CardValue.valueOfLabel("8"));
        Assertions.assertEquals(CardValue.NINE, CardValue.valueOfLabel("9"));
        Assertions.assertEquals(CardValue.TEN, CardValue.valueOfLabel("10"));
        Assertions.assertEquals(CardValue.JACK, CardValue.valueOfLabel("J"));
        Assertions.assertEquals(CardValue.QUEEN, CardValue.valueOfLabel("Q"));
        Assertions.assertEquals(CardValue.KING, CardValue.valueOfLabel("K"));
        Assertions.assertEquals(CardValue.ACE, CardValue.valueOfLabel("A"));
    }

    @Test
    void givenInvalidStringLabel_whenEnumFetched_thenReturnNull() {
        Assertions.assertNull(CardValue.valueOfLabel("X"));
    }
}
