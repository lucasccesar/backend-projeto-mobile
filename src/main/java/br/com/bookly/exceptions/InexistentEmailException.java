package br.com.bookly.exceptions;

public class InexistentEmailException extends RuntimeException {
    public InexistentEmailException(String message) {
        super(message);
    }

    public InexistentEmailException() {
        super("Email inexistent");
    }
}
