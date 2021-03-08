package com.company.customexceptions;

public class StackUnderflowException extends EmptyListException{
    public StackUnderflowException(String message) {
        super(message);
    }
}
