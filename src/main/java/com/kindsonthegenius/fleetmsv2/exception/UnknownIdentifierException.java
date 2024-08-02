package com.kindsonthegenius.fleetmsv2.exception;

/*
In case customer account does not exists in the system for a given email id.
 */
public class UnknownIdentifierException extends Exception {

    public UnknownIdentifierException() {
        super();
    }


    public UnknownIdentifierException(String message) {
        super(message);
    }


    public UnknownIdentifierException(String message, Throwable cause) {
        super(message, cause);
    }
}
