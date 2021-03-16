package com.company.customexceptions;

public class StackOverflowException extends Exception{
    public StackOverflowException(String message) {
        super("StackOverflowException : "+message);
    }
}
