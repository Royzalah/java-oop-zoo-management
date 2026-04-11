package com.example.zoo.ass2.exceptions;

public class GeneralException extends Exception {
    private static final String PRE_MESSAGE = "Error: ";

    public GeneralException(String message) {
        super(PRE_MESSAGE + message);
    }
}
