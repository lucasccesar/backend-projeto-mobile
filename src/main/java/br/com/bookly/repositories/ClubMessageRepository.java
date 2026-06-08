package br.com.bookly.repositories;

import br.com.bookly.entities.ClubMessage;
import br.com.bookly.entities.ParticipantUser;
import jakarta.persistence.Entity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClubMessageRepository extends JpaRepository<ClubMessage, UUID> {
    Page<ClubMessage> findByClub_IdBookClub(UUID idClub, Pageable pageable);
    Page<ClubMessage> findByUser_Id(UUID idUser, Pageable pageable);
}
