package br.com.bookly.exceptions;

public class InexistentColectionException extends RuntimeException {
    public InexistentColectionException(String message) {
        super(message);
    }

    public InexistentColectionException() {
        super("Error: Colection Not Found");
    }
}
