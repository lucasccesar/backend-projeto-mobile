package br.com.bookly.exceptions;

public class InexistentPurchaseException extends RuntimeException {
    public InexistentPurchaseException(String message) {
        super(message);
    }

    public InexistentPurchaseException() {
        super("Purchase Not found");
    }
}
