package com.reporting.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super();
    }
    public UserNotFoundException(String name) {
        super(name);
    }
    public UserNotFoundException(String message, Throwable throwable) {
        super(message,throwable);
    }

}
