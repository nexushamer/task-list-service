package com.zagalabs.tasklist.exceptions;

public class RecordNotFoundException extends ApplicationException {
    public RecordNotFoundException(String description) {
        super(description);
    }
}
