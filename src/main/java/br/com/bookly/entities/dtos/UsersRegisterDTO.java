package br.com.bookly.entities.dtos;

import br.com.bookly.entities.Enums.UserType;

import java.time.LocalDate;

public record UsersRegisterDTO(String name, String email, LocalDate birthday, String password, UserType userType ) {

    //No Java, record é um tipo especial de classe introduzido no Java 16 que serve para representar dados imutáveis de forma simples, reduzindo muito o código.
    //Não é preciso criar get, set e etc
}
