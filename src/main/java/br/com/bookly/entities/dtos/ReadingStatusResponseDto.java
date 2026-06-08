package br.com.bookly.entities.dtos;


import br.com.bookly.entities.Book;
import br.com.bookly.entities.Colection;
import br.com.bookly.entities.Enums.Status;
import br.com.bookly.entities.ReadingStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ReadingStatusResponseDto {

    private UUID id;
    private UUID userId;
    private Status status;
    private BookDTO book;

    public ReadingStatusResponseDto(ReadingStatus readingStatus) {
        this.id = readingStatus.getIdReadingStatus();
        this.userId = readingStatus.getUsers().getId();
        this.status = readingStatus.getStatus();
        this.book = new BookDTO(readingStatus.getBook());
    }

}
