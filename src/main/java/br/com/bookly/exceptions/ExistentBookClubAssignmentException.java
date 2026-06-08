package br.com.bookly.exceptions;

public class ExistentBookClubAssignmentException extends RuntimeException {
    public ExistentBookClubAssignmentException(String message) {
        super(message);
    }

    public ExistentBookClubAssignmentException() {
        super("Error: This book is already assigned to the same club with the same dates!");
    }
}
