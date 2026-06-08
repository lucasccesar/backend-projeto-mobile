package br.com.bookly.services;

import br.com.bookly.entities.ClubMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ClubMessageService {
    public Page<ClubMessage> listClubMessage(Pageable pageable);
    public ClubMessage findClubMessageById(UUID id);
    public Page<ClubMessage> getByClubId(UUID clubId, Pageable pageable);
    public Page<ClubMessage> getByUserId(UUID userId, Pageable pageable);
    public boolean deleteClubMessageById(UUID id);
    public ClubMessage createClubMessage(ClubMessage clubMessage);
    public ClubMessage updateClubMessage(UUID id, ClubMessage clubMessage);
}
