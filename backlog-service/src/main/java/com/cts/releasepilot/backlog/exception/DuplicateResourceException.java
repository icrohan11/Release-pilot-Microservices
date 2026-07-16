package com.cts.releasepilot.backlog.exception;

/** Thrown when creating an entity that violates a uniqueness constraint. Maps to HTTP 409. */
public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message) {
        super(message);
    }
}
