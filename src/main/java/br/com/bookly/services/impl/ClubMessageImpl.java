package br.com.bookly.services.impl;

import br.com.bookly.entities.ClubMessage;
import br.com.bookly.entities.ParticipantUser;
import br.com.bookly.exceptions.BadRequestException;
import br.com.bookly.exceptions.InexistentClubMessageException;
import br.com.bookly.exceptions.NotModifiedClubMessageException;
import br.com.bookly.repositories.ClubMessageRepository;
import br.com.bookly.services.ClubMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ClubMessageImpl implements ClubMessageService {

    @Autowired
    ClubMessageRepository clubMessageRepository;

    @Override
    public Page<ClubMessage> listClubMessage(Pageable pageable) {
        return clubMessageRepository.findAll(pageable);
    }

    @Override
    public ClubMessage findClubMessageById(UUID id) {
        return clubMessageRepository.findById(id).orElseThrow(() -> new InexistentClubMessageException("Error: Message Not Found with this id"));
    }

    @Override
    public Page<ClubMessage> getByClubId(UUID idClub, Pageable pageable) {
        Page<ClubMessage> clubMessages = clubMessageRepository.findByClub_IdBookClub(idClub, pageable);

        return clubMessages;
    }

    @Override
    public Page<ClubMessage> getByUserId(UUID idUser, Pageable pageable) {
        Page<ClubMessage> userMessages = clubMessageRepository.findByUser_Id(idUser, pageable);
        if(userMessages.isEmpty()){
            throw new InexistentClubMessageException("Error: Message Not Found for this user");
        }
        return userMessages;
    }

    @Override
    public boolean deleteClubMessageById(UUID id) {
        if(clubMessageRepository.existsById(id) == false){
            throw new InexistentClubMessageException("Error: Message not found");
        }
        clubMessageRepository.deleteById(id);
        return true;
    }

    @Override
    public ClubMessage createClubMessage(ClubMessage clubMessage) {

        if(clubMessage.getMessage() == null  || clubMessage.getMessage().trim().isEmpty()){
            throw new BadRequestException("Error: Message content cannot be empty");
        }

        if(clubMessage.getUser() == null) {
            throw new BadRequestException("Error: User cannot be null");
        }

        if (clubMessage.getClub() == null){
            throw new BadRequestException("Error: Club cannot be null");
        }

        if(clubMessage.getMessageDate() == null){
            clubMessage.setMessageDate(Timestamp.valueOf(LocalDateTime.now()));
        }

        return clubMessageRepository.save(clubMessage);
    }

    @Override
    public ClubMessage updateClubMessage(UUID id, ClubMessage clubMessage) {

        ClubMessage exists = clubMessageRepository.findById(id).orElse(null);

        if(exists == null){
            throw new InexistentClubMessageException("Error: ClubMessage not found");
        }

        if(clubMessage.getMessage() == null || clubMessage.getMessage().trim().isEmpty()){
            throw new BadRequestException("Error: Message content cannot be empty");
        }

        if(clubMessage.getClub() == null){
            throw new BadRequestException("Error: Club cannot be null");
        }

        if(clubMessage.getUser() == null){
            throw new BadRequestException("Error: User cannot be null");
        }

        if (exists.getMessage().trim().equals(clubMessage.getMessage().trim())) {
            throw new NotModifiedClubMessageException("Error: Message not changed, identical content");
        }

        exists.setMessage(clubMessage.getMessage());
        exists.setUser(clubMessage.getUser());
        exists.setClub(clubMessage.getClub());
        exists.setMessageDate(Timestamp.valueOf(LocalDateTime.now()));

        return clubMessageRepository.save(exists);
    }
}
