package br.com.bookly.repositories;

import br.com.bookly.entities.Rating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RatingRepository extends JpaRepository<Rating, UUID> {
    Optional<Rating> findByUserIdAndBook_IdBook(UUID id, UUID idBook);
    Page<Rating> findByBook_IdBook(UUID idBook, Pageable pageable);
    List<Rating> findByBook_IdBook(UUID idBook);
    Page<Rating> findByUserId(UUID idUser, Pageable pageable);
    boolean existsByUserIdAndBook_IdBook(UUID idUser, UUID idBook);
}
