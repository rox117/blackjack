package com.example.blackjack.Enum;

import java.util.HashMap;
import java.util.Map;

public enum CardValue {
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10),
    ACE("A", 11);

    private static final Map<String, CardValue> BY_LABEL = new HashMap<>();
    static {
        for (CardValue e: values()) {
            BY_LABEL.put(e.label, e);
        }
    }
    public final String label;
    public final int value;

    CardValue(String label, int value){
        this.label = label;
        this.value = value;
    }

    public static CardValue valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }
}
