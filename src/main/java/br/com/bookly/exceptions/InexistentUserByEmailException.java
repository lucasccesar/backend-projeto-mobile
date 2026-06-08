package br.com.bookly.exceptions;

public class InexistentUserByEmailException extends RuntimeException {
    public InexistentUserByEmailException(String message) {
        super(message);
    }
}
