package br.com.bookly.exceptions;

public class TokenGenerationException extends RuntimeException {
    public TokenGenerationException(String message) {
        super(message);
    }
    public TokenGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}