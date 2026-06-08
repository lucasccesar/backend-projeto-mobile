package br.com.bookly.exceptions;

public class ExistingColectionByUserException extends RuntimeException {
    public ExistingColectionByUserException(String message) {
        super(message);
    }

    public ExistingColectionByUserException() {
        super("Error: Colection already exists");
    }
}
