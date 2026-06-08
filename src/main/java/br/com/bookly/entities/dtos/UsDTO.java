package br.com.bookly.entities.dtos;

import br.com.bookly.entities.Enums.UserType;

import java.time.LocalDate;

public record UsDTO(String name, String email, String password, LocalDate birthday, UserType userType) {
}
