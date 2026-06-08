package br.com.bookly.exceptions;

public class InexistentClubMessageException extends RuntimeException {
    public InexistentClubMessageException(String message) {
        super(message);
    }
    public InexistentClubMessageException() {
        super("Error: Message does not exist");
    }
}
