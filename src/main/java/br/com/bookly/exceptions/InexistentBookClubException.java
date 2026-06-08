package br.com.bookly.exceptions;

public class InexistentBookClubException extends RuntimeException {
    public InexistentBookClubException(String message) {
        super(message);
    }

    public InexistentBookClubException() {
      super("Error: Book Club Not Found");
    }
}
