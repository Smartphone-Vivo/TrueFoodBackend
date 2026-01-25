package dev.TrueFood.exceptions;

public class FailUpdateRatingException extends RuntimeException {
    public FailUpdateRatingException(String message) {
        super(message);
    }
}
