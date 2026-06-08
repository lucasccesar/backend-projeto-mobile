package br.com.bookly.exceptions;

public class ExistentAuthorException extends RuntimeException {
    public ExistentAuthorException(String message) {
        super(message);
    }
}
