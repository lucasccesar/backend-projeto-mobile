package br.com.bookly.entities.dtos;


import br.com.bookly.entities.ParticipantUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ParticipantUserDTO {

    private UUID idParticipantUser;
    private UUID userId;
    private UUID clubId;
    private LocalDate entryDate;

    public ParticipantUserDTO(UUID idParticipantUser, UUID userId, UUID clubId, LocalDate entryDate) {
        this.idParticipantUser = idParticipantUser;
        this.userId = userId;
        this.clubId = clubId;
        this.entryDate = entryDate;
    }

    public ParticipantUserDTO(ParticipantUser participantUser) {
        this.idParticipantUser = participantUser.getIdParticipantUser();
        this.userId = participantUser.getUser().getId();
        this.clubId = participantUser.getClub().getIdBookClub();
        this.entryDate = participantUser.getEntryDate();
    }
}
