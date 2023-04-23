package com.example.blackjack.exception;

public class InvalidDeckSizeException extends DeckParseException{
    public InvalidDeckSizeException(String message){
        super(message);
    }
}
