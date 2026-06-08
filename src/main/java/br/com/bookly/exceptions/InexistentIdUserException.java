package br.com.bookly.exceptions;

public class InexistentIdUserException extends RuntimeException {
    public InexistentIdUserException(String message) {
        super(message);
    }

    public  InexistentIdUserException(){
        super("Error: Id User Not Found");
    }
}
