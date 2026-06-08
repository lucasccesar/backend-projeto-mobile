package br.com.bookly.entities.dtos;


import br.com.bookly.entities.Book;
import br.com.bookly.entities.Enums.Status;
import br.com.bookly.entities.Enums.UserType;
import br.com.bookly.entities.ReadingStatus;
import br.com.bookly.entities.Users;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class ReadingStatusDto {

    private UUID id;
    private Status status;
    private BookDTO book;
    private UsersDTO user;

    public ReadingStatusDto(ReadingStatus readingStatus) {
        this.id = readingStatus.getIdReadingStatus();
        this.status = readingStatus.getStatus();
        this.book = new BookDTO(readingStatus.getBook());
        this.user = new UsersDTO(readingStatus.getUsers());
    }
}
