package com.cts.releasepilot.release.exception;

/** Thrown when an operation is not valid for the current state. Maps to HTTP 400. */
public class InvalidOperationException extends RuntimeException {
    public InvalidOperationException(String message) {
        super(message);
    }
}
