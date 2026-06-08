package br.com.bookly.exceptions;

public class ExistingParticipantUserException extends RuntimeException {
    public ExistingParticipantUserException(String message) {
        super(message);
    }
    public ExistingParticipantUserException() {
        super("Error: User is already a participant of this BookClub");
    }
}
