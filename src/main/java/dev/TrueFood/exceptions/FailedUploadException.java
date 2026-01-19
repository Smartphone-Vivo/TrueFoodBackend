package dev.TrueFood.exceptions;

public class FailedUploadException extends RuntimeException {
    public FailedUploadException(String message) {
        super(message);
    }
}
