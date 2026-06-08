package br.com.bookly.repositories;

import br.com.bookly.entities.Purchase;
import br.com.bookly.entities.PurchaseBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, UUID> {

    Page<Purchase> findByPurchaseDate(LocalDate date, Pageable pageable);
    Page<Purchase> findByUserId_id(UUID id, Pageable pageable);
}