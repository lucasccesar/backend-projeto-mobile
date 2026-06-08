package br.com.bookly.exceptions;

public class InexistentParticipantUserException extends RuntimeException {
    public InexistentParticipantUserException(String message) {

        super(message);
    }
    public InexistentParticipantUserException() {
        super("Error: Participant User Not Found");
    }
}
