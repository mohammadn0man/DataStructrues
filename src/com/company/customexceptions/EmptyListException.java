package com.company.customexceptions;

public class EmptyListException extends Exception {
    public EmptyListException(String message) {
        super("EmptyListException : " + message);
    }
}
