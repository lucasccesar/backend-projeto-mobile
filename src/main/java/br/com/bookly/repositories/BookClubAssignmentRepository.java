package br.com.bookly.repositories;

import br.com.bookly.entities.BookClubAssignment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface BookClubAssignmentRepository extends JpaRepository<BookClubAssignment, UUID> {
    Page<BookClubAssignment> findByBook_IdBook(UUID bookId, Pageable pageable); //busca todos os clubes que um livro esta
    Page<BookClubAssignment> findByBookClub_IdBookClub(UUID bookClubId, Pageable pageable); //busca todos os livros de um clube
    boolean existsByBook_IdBookAndBookClub_IdBookClubAndStartDateAndFinishDate(UUID bookId, UUID bookClubId, LocalDate startDate, LocalDate finishDate);
}