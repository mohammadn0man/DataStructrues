package com.company.customexceptions;

public class QueueIsFullException extends Exception {
    public QueueIsFullException(String message) {
        super("QueueIsFullException : " + message);
    }
}
