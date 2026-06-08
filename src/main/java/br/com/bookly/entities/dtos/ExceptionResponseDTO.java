package br.com.bookly.entities.dtos;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ExceptionResponseDTO {

    private String message;
    private int statusCode;
    private String request;
    private LocalDateTime timestamp;

    public ExceptionResponseDTO(String message, int statusCode, String request) {
        this.message = message;
        this.statusCode = statusCode;
        this.request = request;
        this.timestamp = LocalDateTime.now();
    }
}