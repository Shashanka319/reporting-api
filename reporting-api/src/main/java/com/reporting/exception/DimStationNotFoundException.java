package com.reporting.exception;

public class DimStationNotFoundException extends Exception {
    public DimStationNotFoundException() { super(); }
    public DimStationNotFoundException(String message) { super(message); }
    public DimStationNotFoundException(String message, Throwable throwable) { super(message, throwable); }
}