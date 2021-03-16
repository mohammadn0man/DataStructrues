package com.company.customexceptions;

public class InvalidIndexException extends Exception {
    public InvalidIndexException(String message) {
        super("InvalidIndexException : " + message);
    }
}
