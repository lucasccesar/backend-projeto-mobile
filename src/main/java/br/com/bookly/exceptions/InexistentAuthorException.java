package br.com.bookly.exceptions;

public class InexistentAuthorException extends RuntimeException {
    public InexistentAuthorException(String message) {
        super(message);
    }

    public InexistentAuthorException() {
        super("Error: Author not found");
    }
}
