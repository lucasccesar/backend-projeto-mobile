package br.com.bookly.repositories;

import br.com.bookly.entities.BookClub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.UUID;

@Repository
public interface BookClubRepository extends JpaRepository<BookClub, UUID> {
    boolean existsByNameIgnoreCase(String name);
    boolean existsById(UUID id);
    BookClub findBookClubByNameIgnoreCase(String name);
}
