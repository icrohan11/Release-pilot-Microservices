package com.cts.releasepilot.communication.exception;

/** Thrown when an operation is not permitted in the current state. Maps to HTTP 400. */
public class InvalidOperationException extends RuntimeException {
    public InvalidOperationException(String message) {
        super(message);
    }
}
