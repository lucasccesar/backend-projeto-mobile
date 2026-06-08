package br.com.bookly.exceptions;

public class InexistentPurchaseBookException extends RuntimeException {
    public InexistentPurchaseBookException(String message) {
        super(message);
    }

    public InexistentPurchaseBookException() {
        super("Error: Purchase book ID does not exist");
    }
}
