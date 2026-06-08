package br.com.bookly.exceptions;

public class InexistentBookClubAssignmentException extends RuntimeException {
    public InexistentBookClubAssignmentException(String message) {
        super(message);
    }
    public InexistentBookClubAssignmentException() {
        super("Error: BookClub assignment Not Found");
    }
}
