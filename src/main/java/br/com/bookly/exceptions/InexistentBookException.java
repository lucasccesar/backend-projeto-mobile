package br.com.bookly.exceptions;

public class InexistentBookException extends RuntimeException {
    public InexistentBookException(String message) {
        super(message);
    }

    public InexistentBookException() {
        super("Error: Book Not Found");
    }
}
