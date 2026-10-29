package com.reporting.exception;

public class DimRouteNotFoundException extends Exception {
    public DimRouteNotFoundException() { super(); }
    public DimRouteNotFoundException(String message) { super(message); }
    public DimRouteNotFoundException(String message, Throwable throwable) { super(message, throwable); }
}