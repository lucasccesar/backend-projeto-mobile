package br.com.bookly.exceptions;

public class DifferentException extends RuntimeException {
    public DifferentException(String message) {
        super(message);
    }

    public DifferentException() {
        super("Error: Different.");
    }
}
