package com.reporting.exception;

public class DimMetroCardNotFoundException extends Exception {
    public DimMetroCardNotFoundException() { super(); }
    public DimMetroCardNotFoundException(String message) { super(message); }
    public DimMetroCardNotFoundException(String message, Throwable throwable) { super(message, throwable); }
}