package br.com.bookly.entities.dtos;

import br.com.bookly.entities.Author;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class AuthorDTO {

    private UUID id;
    private String name;

    public AuthorDTO(Author author) {
        this.id = author.getIdAuthor();
        this.name = author.getName();
    }

}
