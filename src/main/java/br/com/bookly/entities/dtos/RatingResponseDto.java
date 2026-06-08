package br.com.bookly.entities.dtos;

import br.com.bookly.entities.Book;
import br.com.bookly.entities.Rating;
import br.com.bookly.entities.Users;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class RatingResponseDto {

    private UUID id;
    private UUID user;
    private UUID book;
    private String comment;
    private int ratingValue;
    private LocalDate ratingDate;

    public RatingResponseDto(Rating rating) {
        this.id = rating.getIdRating();
        this.user = rating.getUser().getId();
        this.book = rating.getBook().getIdBook();
        this.comment = rating.getComment();
        this.ratingValue = rating.getRatingValue();
        this.ratingDate = rating.getRatingDate();
    }
}
