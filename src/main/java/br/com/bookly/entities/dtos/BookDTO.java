package br.com.bookly.entities.dtos;

import br.com.bookly.entities.Book;
import br.com.bookly.entities.Enums.UserType;
import br.com.bookly.entities.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class BookDTO {
    private UUID id;
    private String title;
    private String synopsis;
    private String genre;
    private int availableQuantity;
    private BigDecimal price;
    private List<AuthorDTO> authors;

    public BookDTO(UUID id, String title, String synopsis, String genre, int availableQuantity, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.synopsis = synopsis;
        this.genre = genre;
        this.availableQuantity = availableQuantity;
        this.price = price;
    }

    public BookDTO(Book book){
        this.id = book.getIdBook();
        this.title = book.getTitle();
        this.synopsis = book.getSynopsis();
        this.genre = book.getGenre();
        this.availableQuantity = book.getAvailableQuantity();
        this.price = book.getPrice();
        this.authors = book.getAuthors()
                .stream()
                .map(AuthorDTO::new)
                .collect(Collectors.toList());
    }
}
