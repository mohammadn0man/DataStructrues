package com.company.customexceptions;

public class QueueIsEmptyException extends Exception {
    public QueueIsEmptyException(String message) {
        super("QueueIsEmptyException : " + message);
    }
}
