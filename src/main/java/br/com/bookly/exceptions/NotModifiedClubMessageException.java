package br.com.bookly.exceptions;

public class NotModifiedClubMessageException extends RuntimeException {
    public NotModifiedClubMessageException(String message) {
        super(message);
    }
    public NotModifiedClubMessageException() {
      super("Message not changed");
    }
}
