package com.reporting.exception;

public class FactPaymentTransactionNotFoundException extends Exception {
    public FactPaymentTransactionNotFoundException() { super(); }
    public FactPaymentTransactionNotFoundException(String message) { super(message); }
    public FactPaymentTransactionNotFoundException(String message, Throwable throwable) { super(message, throwable); }
}