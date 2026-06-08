package br.com.bookly.exceptions;

public class InvalidReadingStatusException extends RuntimeException {
    public InvalidReadingStatusException(String message) {
        super(message);
    }
}
