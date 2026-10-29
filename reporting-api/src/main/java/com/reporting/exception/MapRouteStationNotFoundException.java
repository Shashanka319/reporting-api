package com.reporting.exception;

public class MapRouteStationNotFoundException extends Exception {
    public MapRouteStationNotFoundException() {
        super();
    }
    public MapRouteStationNotFoundException(String message) {
        super(message);
    }
    public MapRouteStationNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}