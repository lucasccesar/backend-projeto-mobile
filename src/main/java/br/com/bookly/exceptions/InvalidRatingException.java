package br.com.bookly.exceptions;

public class InvalidRatingException extends RuntimeException {
    public InvalidRatingException(String message) {
        super(message);
    }

    public InvalidRatingException() {
        super("Error: Invalid rating");
    }
}
