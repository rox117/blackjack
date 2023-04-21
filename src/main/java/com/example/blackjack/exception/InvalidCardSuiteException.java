package com.example.blackjack.exception;

public class InvalidCardSuiteException extends DeckParseException{
    public InvalidCardSuiteException(String errorMessage){
        super(errorMessage);
    }
}
