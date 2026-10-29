package com.reporting.exception;

public class DimUserNotFoundException extends Exception {
    public DimUserNotFoundException() { super(); }
    public DimUserNotFoundException(String message) { super(message); }
    public DimUserNotFoundException(String message, Throwable throwable) { super(message, throwable); }
}