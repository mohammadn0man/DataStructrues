package com.company.customexceptions;

public class InvalidPositionException extends Exception {
    public InvalidPositionException(String message) {
        super(message);
    }
}
