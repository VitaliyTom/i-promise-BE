package com.exadel.ipromise.exception;

public class UserIsInvalidAuthException extends RuntimeException {
    public UserIsInvalidAuthException(String message) {
        super(message);
    }
}
