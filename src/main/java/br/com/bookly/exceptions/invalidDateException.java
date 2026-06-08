package br.com.bookly.exceptions;

public class invalidDateException extends RuntimeException {
    public invalidDateException(String message) {
        super(message);
    }
}
