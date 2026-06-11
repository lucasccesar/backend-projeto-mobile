package br.com.bookly.entities.dtos;


import br.com.bookly.entities.BookClub;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class BookClubDTO {

    private UUID id;
    private String name;
    private String theme;
    private String description;
    private String frequency;

    public BookClubDTO(UUID id, String name, String theme, String description, String frequency) {
        this.id = id;
        this.name = name;
        this.theme = theme;
        this.description = description;
        this.frequency = frequency;
    }

    public BookClubDTO(BookClub bookClub) {
        this.id = bookClub.getIdBookClub();
        this.name = bookClub.getName();
        this.theme = bookClub.getTheme();
        this.description = bookClub.getDescription();
        this.frequency = bookClub.getFrequency();
    }
}
