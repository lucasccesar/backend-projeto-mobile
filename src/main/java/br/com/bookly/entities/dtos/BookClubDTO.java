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
    private UUID creatorId;

    public BookClubDTO(BookClub bookClub) {
        this.id = bookClub.getIdBookClub();
        this.name = bookClub.getName();
        this.theme = bookClub.getTheme();
        this.description = bookClub.getDescription();
        this.frequency = bookClub.getFrequency();
        this.creatorId = bookClub.getCreator() != null
                ? bookClub.getCreator().getId()
                : null;
    }
}
