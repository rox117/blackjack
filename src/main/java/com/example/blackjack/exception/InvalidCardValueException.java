package com.example.blackjack.exception;

public class InvalidCardValueException extends DeckParseException{
    public InvalidCardValueException(String errorMessage){
        super(errorMessage);
    }
}
