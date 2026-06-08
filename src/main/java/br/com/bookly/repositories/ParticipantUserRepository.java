package br.com.bookly.repositories;

import br.com.bookly.entities.ParticipantUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ParticipantUserRepository extends JpaRepository<ParticipantUser, UUID> {
    Page<ParticipantUser> findByClub_IdBookClub(UUID idClub, Pageable pageable); // lista todos os users de um clube
    Page<ParticipantUser> findByUser_Id(UUID idUser, Pageable pageable); // lista todos os clubes de um usuario
    boolean existsByUser_IdAndClub_IdBookClub(UUID userId, UUID clubId);
}
