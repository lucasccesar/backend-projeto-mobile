package br.com.bookly.exceptions;

public class InexistentRatingException extends RuntimeException {
    public InexistentRatingException(String message) {
        super(message);
    }

    public InexistentRatingException() {
        super("Error: Rating not found");
    }
}
