package com.example.blackjack.Enum;

import java.util.HashMap;
import java.util.Map;

public enum Suit {
    CLUB("C"),
    DIAMOND("D"),
    HEART("H"),
    SPADE("S");

    private static final Map<String, Suit> BY_LABEL = new HashMap<>();
    static {
        for (Suit e: values()) {
            BY_LABEL.put(e.label, e);
        }
    }
    public final String label;
    Suit(String label){
        this.label = label;
    }
    public static Suit valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }

}
