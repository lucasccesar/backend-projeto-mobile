package br.com.bookly.services.impl;

import br.com.bookly.entities.ParticipantUser;
import br.com.bookly.exceptions.BadRequestException;
import br.com.bookly.exceptions.ExistingParticipantUserException;
import br.com.bookly.exceptions.InexistentParticipantUserException;
import br.com.bookly.repositories.ParticipantUserRepository;
import br.com.bookly.services.ParticipantUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class ParticipantUserServiceImpl implements ParticipantUserService {

    @Autowired
    ParticipantUserRepository participantUserRepository;


    @Override
    public ParticipantUser createParticipantUser(ParticipantUser participantUser) {

        if (participantUser.getUser() == null){
            throw new BadRequestException("Error: User is required");
        }

        if(participantUser.getClub() == null){
            throw new BadRequestException("Error: Club is required");
        }

        boolean exists = participantUserRepository.existsByUser_IdAndClub_IdBookClub(
                participantUser.getUser().getId(),
                participantUser.getClub().getIdBookClub()
        );

        if (exists) {
            throw new ExistingParticipantUserException("Error: User is already a participant of this BookClub");
        }

        if (participantUser.getEntryDate() == null) {
            participantUser.setEntryDate(LocalDate.now());
        }

        return participantUserRepository.save(participantUser);
    }

    @Override
    public ParticipantUser updateParticipantUser(UUID id, ParticipantUser participantUser) {

        ParticipantUser exists = participantUserRepository.findById(id).orElse(null);

        if (exists == null) {
            throw new InexistentParticipantUserException("Error: Participant User Not Found");
        }

        if (participantUser.getEntryDate() != null) {
            exists.setEntryDate(participantUser.getEntryDate());
        } else {
            exists.setEntryDate(exists.getEntryDate());
        }

        if(participantUser.getUser() == null){
            throw new BadRequestException("Error: User is required");
        }
        if(participantUser.getClub() == null){
            throw new BadRequestException("Error: Club is required");
        }

        exists.setClub(participantUser.getClub());
        exists.setUser(participantUser.getUser());

        return participantUserRepository.save(exists);
    }

    @Override
    public boolean deleteParticipantUserById(UUID id) {

        if(participantUserRepository.existsById(id) == false){
            throw new InexistentParticipantUserException("Error: Participant User Not Found");
        }

        participantUserRepository.deleteById(id);
        return true;
    }

    @Override
    public ParticipantUser getParticipantUser(UUID id) {
        return participantUserRepository.findById(id).
                orElseThrow(() -> new InexistentParticipantUserException("Error: Participant User Not Found with this id"));
    }

    @Override
    public Page<ParticipantUser> getParticipantUsers(Pageable pageable) {
        return participantUserRepository.findAll(pageable);
    }

    @Override
    public Page<ParticipantUser> getByUserId(UUID idUser, Pageable pageable) {
        Page<ParticipantUser> participantUsers = participantUserRepository.findByUser_Id(idUser, pageable);
        if(participantUsers.isEmpty()){
            throw new InexistentParticipantUserException("Error: No clubs found for this User");
        }
        return participantUsers;
    }

    @Override
    public Page<ParticipantUser> getByClubId(UUID idClub, Pageable pageable) {
        Page<ParticipantUser> participantUsers =
                participantUserRepository.findByClub_IdBookClub(idClub, pageable);

        return participantUsers;
    }
}
