package com.reporting.exception;

public class FactTripRecordNotFoundException extends Exception {
    public FactTripRecordNotFoundException() { super(); }
    public FactTripRecordNotFoundException(String message) { super(message); }
    public FactTripRecordNotFoundException(String message, Throwable throwable) { super(message, throwable); }
}