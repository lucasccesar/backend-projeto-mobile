package br.com.bookly.exceptions;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException(String message) {
        super(message);
    }
    public InvalidTokenException() {
      super("Error: Invalid or expired token!");
    }
}
