package br.com.bookly.services;

import br.com.bookly.entities.ParticipantUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.UUID;

public interface ParticipantUserService {
    public ParticipantUser createParticipantUser(ParticipantUser participantUser);
    public ParticipantUser updateParticipantUser(UUID id, ParticipantUser participantUser);
    public boolean deleteParticipantUserById(UUID id);
    public ParticipantUser getParticipantUser(UUID id);
    public Page<ParticipantUser> getParticipantUsers(Pageable pageable);
    public Page<ParticipantUser> getByClubId(UUID idClub, Pageable pageable);
    public Page<ParticipantUser> getByUserId(UUID idUser, Pageable pageable);
}
