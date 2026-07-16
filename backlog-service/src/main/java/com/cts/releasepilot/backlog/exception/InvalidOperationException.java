package com.cts.releasepilot.backlog.exception;

/** Thrown when an operation is not valid in the current context. Maps to HTTP 400. */
public class InvalidOperationException extends RuntimeException {
    public InvalidOperationException(String message) {
        super(message);
    }
}
