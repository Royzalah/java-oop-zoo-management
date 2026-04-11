package com.example.zoo.ass2.exceptions;

public class PatternException extends GeneralException {
    private static final String MESSAGE = "Invalid Pattern";

    public PatternException() {
        super(MESSAGE);
    }
}
