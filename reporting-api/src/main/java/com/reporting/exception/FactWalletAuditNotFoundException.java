package com.reporting.exception;

public class FactWalletAuditNotFoundException extends Exception {
    public FactWalletAuditNotFoundException() { super(); }
    public FactWalletAuditNotFoundException(String message) { super(message); }
    public FactWalletAuditNotFoundException(String message, Throwable throwable) { super(message, throwable); }
}