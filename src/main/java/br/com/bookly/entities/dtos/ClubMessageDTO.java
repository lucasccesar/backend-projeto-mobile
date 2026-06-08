package br.com.bookly.entities.dtos;

import br.com.bookly.entities.ClubMessage;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
public class ClubMessageDTO {

    private UUID idClubMessage;
    private UUID userId;
    private UUID clubId;
    private String message;
    private Timestamp messageDate;

    public ClubMessageDTO(UUID idClubMessage, UUID userId, UUID clubId, String message, Timestamp messageDate) {
        this.idClubMessage = idClubMessage;
        this.userId = userId;
        this.clubId = clubId;
        this.message = message;
        this.messageDate = messageDate;
    }

    public ClubMessageDTO(ClubMessage clubMessage) {
        this.idClubMessage = clubMessage.getIdClubMessage();
        this.userId = clubMessage.getUser().getId();
        this.clubId = clubMessage.getClub().getIdBookClub();
        this.message = clubMessage.getMessage();
        this.messageDate = clubMessage.getMessageDate();
    }
}
