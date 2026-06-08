package br.com.bookly.repositories;

import br.com.bookly.entities.Colection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ColectionRepository extends JpaRepository<Colection, UUID> {
    boolean existsByNameIgnoreCase(String name);
    Colection findByNameIgnoreCase(String name);
    Page<Colection> findByUser_Id(UUID userId, Pageable pageable);
    boolean existsByNameIgnoreCaseAndUser_Id(String name, UUID userId);
}
