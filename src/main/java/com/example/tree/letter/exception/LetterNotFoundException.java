package com.example.tree.letter.exception;

public class LetterNotFoundException extends RuntimeException {
    public LetterNotFoundException(String message) {
        super(message);
    }
}
