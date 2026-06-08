package br.com.bookly.entities.dtos;

import br.com.bookly.entities.Colection;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
public class ColectionRespondeDTO {

    private UUID id;
    private UUID userId;
    private String description;
    private String name;
    private List<BookDTO> books;

    public ColectionRespondeDTO(Colection colection) {
        this.id = colection.getIdColection();
        this.userId = colection.getUser().getId();
        this.description = colection.getDescription();
        this.name = colection.getName();
        this.books = colection.getBooks()
                .stream()
                .map(BookDTO::new)
                .collect(Collectors.toList());
    }
}
