package com.company.customexceptions;

public class EmptyTreeException extends Exception {
    public EmptyTreeException(String message) {
        super("EmptyTreeException : " + message);
    }
}
