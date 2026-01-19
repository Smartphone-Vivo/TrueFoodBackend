package dev.TrueFood.exceptions;

public class EmailIsAlreadyUse extends RuntimeException {
    public EmailIsAlreadyUse(String message) {
        super(message);
    }
}
