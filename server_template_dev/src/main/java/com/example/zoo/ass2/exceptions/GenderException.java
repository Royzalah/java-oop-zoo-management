package com.example.zoo.ass2.exceptions;

public class GenderException extends GeneralException {
    private static final String MESSAGE = "Invalid Gender";

    public GenderException() {
        super(MESSAGE);
    }
}
