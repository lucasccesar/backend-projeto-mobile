package br.com.bookly.exceptions;

public class ReadingStatusNotFoundException extends RuntimeException {
    public ReadingStatusNotFoundException(String message) {
        super(message);
    }
}
