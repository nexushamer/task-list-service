package com.zagalabs.tasklist.exceptions;

public class ApplicationException extends RuntimeException {
    private final String description;

    public ApplicationException(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
