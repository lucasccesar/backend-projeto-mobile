package br.com.bookly.exceptions;

public class ExistentEmailException extends RuntimeException {
    public ExistentEmailException(String message) {
        super(message);
    }
}
