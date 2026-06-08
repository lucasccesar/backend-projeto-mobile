package br.com.bookly.exceptions;

public class ExistentBookClubException extends RuntimeException {
    public ExistentBookClubException(String message) {
        super(message);
    }
    public ExistentBookClubException() {
        super("Error: BookClub already exists");
    }
}
