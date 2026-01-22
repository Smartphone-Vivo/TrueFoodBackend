package dev.TrueFood.exceptions;

public class UserAlreadyWorkerException extends RuntimeException {
    public UserAlreadyWorkerException(String message) {
        super(message);
    }
}
