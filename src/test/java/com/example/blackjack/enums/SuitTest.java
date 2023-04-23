package com.example.blackjack.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SuitTest {
    @Test
    void givenValidSuiteString_whenConvertedToSuite_thenReturnValidSuite() {
        Assertions.assertEquals(Suit.CLUB, Suit.valueOfLabel("C"));
        Assertions.assertEquals(Suit.DIAMOND, Suit.valueOfLabel("D"));
        Assertions.assertEquals(Suit.HEART, Suit.valueOfLabel("H"));
        Assertions.assertEquals(Suit.SPADE, Suit.valueOfLabel("S"));
    }

    @Test
    void givenInvalidSuiteString_whenConvertedToSuite_thenReturnNull() {
        Assertions.assertNull(Suit.valueOfLabel("A"));
        Assertions.assertNull(Suit.valueOfLabel("B"));
        Assertions.assertNull(Suit.valueOfLabel("N"));
        Assertions.assertNull(Suit.valueOfLabel("F"));
    }
}
